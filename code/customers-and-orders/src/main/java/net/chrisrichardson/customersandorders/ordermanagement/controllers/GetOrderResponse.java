package net.chrisrichardson.customersandorders.ordermanagement.controllers;

import net.chrisrichardson.customersandorders.ordermanagement.domain.Order;
import net.chrisrichardson.customersandorders.ordermanagement.domain.OrderStatus;

public class GetOrderResponse {
  private OrderStatus status;
  private String orderTotal;

  private GetOrderResponse() {
  }

  public GetOrderResponse(OrderStatus status, String orderTotal) {
    this.status = status;
    this.orderTotal = orderTotal;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public String getOrderTotal() {
    return orderTotal;
  }
}
