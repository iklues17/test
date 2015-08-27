package net.chrisrichardson.customersandorders.ordermanagement.services;

public class PlaceOrderResult {
  private final String orderId;

  public PlaceOrderResult(String orderId) {
    this.orderId = orderId;

  }

  public String getOrderId() {
    return orderId;
  }
}
