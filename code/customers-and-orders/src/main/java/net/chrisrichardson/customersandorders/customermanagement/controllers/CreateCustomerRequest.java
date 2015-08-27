package net.chrisrichardson.customersandorders.customermanagement.controllers;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

import java.math.BigDecimal;

public class CreateCustomerRequest {
  private String name;
  private String creditLimit;

  private CreateCustomerRequest() {
  }


  public CreateCustomerRequest(String name, String creditLimit) {
    this.name = name;
    this.creditLimit = creditLimit;
  }

  public String getName() {
    return name;
  }

  public Money getCreditLimitAsMoney() {
    return new Money(new BigDecimal(creditLimit));
  }


}
