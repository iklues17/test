package net.chrisrichardson.customersandorders.ordermanagement.controllers;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

import java.math.BigDecimal;

public class CreateOrderRequest {
  private String customerId;
  private String orderTotal;

  private CreateOrderRequest() {
  }

  public CreateOrderRequest(String customerId, String orderTotal) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
  }

  public String getCustomerId() {
    return customerId;
  }

  public Money getOrderTotalAsMoney() {
    return new Money(new BigDecimal(orderTotal));
  }
}
