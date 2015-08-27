package net.chrisrichardson.customersandorders.customermanagement.controllers;

public class CreateCustomerResponse {

  private String customerId;

  private CreateCustomerResponse() {
  }

  public CreateCustomerResponse(String customerId) {
    this.customerId = customerId;

  }

  public String getCustomerId() {
    return customerId;
  }
}
