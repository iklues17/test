package net.chrisrichardson.customersandorders;

import net.chrisrichardson.customersandorders.customermanagement.controllers.CreateCustomerRequest;
import net.chrisrichardson.customersandorders.customermanagement.controllers.CreateCustomerResponse;
import net.chrisrichardson.customersandorders.customermanagement.controllers.GetCustomerResponse;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.CreateOrderRequest;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.CreateOrderResponse;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.GetAvailableCreditResponse;
import net.chrisrichardson.customersandorders.ordermanagement.controllers.GetOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CustomerOrderClient {

  private final int port;
  private final RestTemplate restTemplate;

  public CustomerOrderClient(int port, RestTemplate restTemplate) {
    this.port = port;
    this.restTemplate = restTemplate;
  }

  private String makeUrl(String path) {
    return "http://localhost:" + port + path;
  }


  public GetOrderResponse getOrder(String orderId) {
    ResponseEntity<GetOrderResponse> getOrderResponseEntity = restTemplate.getForEntity(makeUrl("/orders/{orderId}"), GetOrderResponse.class, orderId);
    assertEquals(HttpStatus.OK, getOrderResponseEntity.getStatusCode());
    return getOrderResponseEntity.getBody();
  }

  public CreateOrderResponse createOrder(String customerId, BigDecimal orderTotal) {
    ResponseEntity<CreateOrderResponse> createOrderResponseEntity = restTemplate.postForEntity(makeUrl("/orders"), new CreateOrderRequest(customerId, orderTotal.toPlainString()), CreateOrderResponse.class);
    assertEquals(HttpStatus.OK, createOrderResponseEntity.getStatusCode());

    return createOrderResponseEntity.getBody();
  }

  public GetAvailableCreditResponse getAvailableCredit(String customerId) {
    ResponseEntity<GetAvailableCreditResponse> availableCreditResponseEntity = restTemplate.getForEntity(makeUrl("/customers/{customerId}/availablecredit"), GetAvailableCreditResponse.class, customerId);
    assertEquals(HttpStatus.OK, availableCreditResponseEntity.getStatusCode());
    return availableCreditResponseEntity.getBody();
  }

  public GetCustomerResponse getCustomer(String customerId) {
    ResponseEntity<GetCustomerResponse> getCustomerResponseEntity = restTemplate.getForEntity(makeUrl("/customers/{customerId}"), GetCustomerResponse.class, customerId);
    assertEquals(HttpStatus.OK, getCustomerResponseEntity.getStatusCode());

    return getCustomerResponseEntity.getBody();
  }

  public CreateCustomerResponse createCustomer(String customerName, BigDecimal creditLimit) {
    ResponseEntity<CreateCustomerResponse> createCustomerResponseEntity = restTemplate.postForEntity(makeUrl("/customers"), new CreateCustomerRequest(customerName, creditLimit.toPlainString()), CreateCustomerResponse.class);
    assertEquals(HttpStatus.OK, createCustomerResponseEntity.getStatusCode());

    return createCustomerResponseEntity.getBody();
  }


}
