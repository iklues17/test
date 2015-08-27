package net.chrisrichardson.customersandorders.ordermanagement.services;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

public class PlaceOrderRequest {
  private String customerId;
  private Money orderTotal;

  public PlaceOrderRequest(String customerId, Money orderTotal) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
  }

  public String getCustomerId() {
    return customerId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }
}
