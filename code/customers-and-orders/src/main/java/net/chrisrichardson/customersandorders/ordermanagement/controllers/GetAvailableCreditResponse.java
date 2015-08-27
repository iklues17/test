package net.chrisrichardson.customersandorders.ordermanagement.controllers;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

public class GetAvailableCreditResponse {
  private String availableCredit;

  private GetAvailableCreditResponse() {
  }

  public GetAvailableCreditResponse(Money availableCredit) {
    this.availableCredit = availableCredit.asString();
  }

  public String getAvailableCredit() {
    return availableCredit;
  }
}
