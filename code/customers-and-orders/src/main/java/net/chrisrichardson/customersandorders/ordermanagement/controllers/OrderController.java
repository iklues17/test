package net.chrisrichardson.customersandorders.ordermanagement.controllers;

import net.chrisrichardson.customersandorders.ordermanagement.domain.Order;
import net.chrisrichardson.customersandorders.ordermanagement.services.OrderService;
import net.chrisrichardson.customersandorders.ordermanagement.services.PlaceOrderRequest;
import net.chrisrichardson.customersandorders.ordermanagement.services.PlaceOrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @RequestMapping(value="/orders", method= RequestMethod.POST)
  public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
    PlaceOrderResult order = orderService.placeOrder(new PlaceOrderRequest(request.getCustomerId(), request.getOrderTotalAsMoney()));
    return new CreateOrderResponse(order.getOrderId());
  }

  @RequestMapping(value="/orders/{orderId}", method= RequestMethod.GET)
  public GetOrderResponse getOrder(@PathVariable String orderId) {
    Order order = orderService.getOrder(orderId);
    return new GetOrderResponse(order.getStatus(), order.getOrderTotal().asString());
  }
}
