package net.chrisrichardson.customersandorders.ordermanagement.controllers;

public class CreateOrderResponse {
  private String orderId;

  private CreateOrderResponse() {
  }

  public CreateOrderResponse(String orderId) {
    this.orderId = orderId;

  }

  public String getOrderId() {
    return orderId;
  }
}
