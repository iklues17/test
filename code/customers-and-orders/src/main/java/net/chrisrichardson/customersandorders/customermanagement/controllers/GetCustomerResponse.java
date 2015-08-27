package net.chrisrichardson.customersandorders.customermanagement.controllers;

public class GetCustomerResponse {
  private String name;

  private GetCustomerResponse() {
  }

  public GetCustomerResponse(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
