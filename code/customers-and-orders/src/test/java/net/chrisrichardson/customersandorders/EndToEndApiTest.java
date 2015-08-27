package net.chrisrichardson.customersandorders;

import net.chrisrichardson.customersandorders.customermanagement.controllers.CreateCustomerResponse;
import net.chrisrichardson.customersandorders.customermanagement.controllers.GetCustomerResponse;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.CreateOrderResponse;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.GetAvailableCreditResponse;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.GetOrderResponse;
import net.chrisrichardson.customersandorders.ordermanagement.domain.OrderStatus;
import net.chrisrichardson.getataxi.testutil.async.Eventually;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EndToEndTestConfiguration.class)
@WebIntegrationTest(randomPort = true)
public class EndToEndApiTest {

  @Value("${local.server.port}")
  private int port;

  @Autowired
  private RestTemplate restTemplate;

  private CustomerOrderClient customerOrderClient;

  @PostConstruct
  public void makeClient() {
    customerOrderClient = new CustomerOrderClient(port, restTemplate);
  }



  // TODO - what does this give you
  // private RestTemplate template = new TestRestTemplate();

  @Test
  public void shouldCreateCustomerAndOrder() {

    String customerName = "Fred";
    BigDecimal creditLimit = new BigDecimal("1024");

    CreateCustomerResponse createCustomerResponse = customerOrderClient.createCustomer(customerName, creditLimit);
    String customerId = createCustomerResponse.getCustomerId();
    assertNotNull(customerId);

    System.out.println("CustomerId=" + customerId);

    GetCustomerResponse getCustomerResponse = customerOrderClient.getCustomer(customerId);
    assertEquals(customerName, getCustomerResponse.getName());

    Eventually.eventually( () -> {
      GetAvailableCreditResponse getAvailableCreditResponse = customerOrderClient.getAvailableCredit(customerId);
      assertEquals(creditLimit.toPlainString(), getAvailableCreditResponse.getAvailableCredit());
    });

    BigDecimal orderTotal = new BigDecimal("256");

    CreateOrderResponse createOrderResponse = customerOrderClient.createOrder(customerId, orderTotal);
    String orderId = createOrderResponse.getOrderId();
    assertNotNull(orderId);
    System.out.println("orderId=" + orderId);

    Eventually.eventually( () -> {
      GetOrderResponse getOrderResponse = customerOrderClient.getOrder(orderId);
      assertEquals(orderTotal.toPlainString(), getOrderResponse.getOrderTotal());
      assertEquals(OrderStatus.OPEN, getOrderResponse.getStatus());
    });

    Eventually.eventually( () -> {
      GetAvailableCreditResponse getAvailableCreditResponse = customerOrderClient.getAvailableCredit(customerId);
      assertEquals(creditLimit.subtract(orderTotal).toPlainString(), getAvailableCreditResponse.getAvailableCredit());
    });

  }



}
