package net.chrisrichardson.customersandorders.commondomain.domainevents;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.getataxi.events.Event;

public class CustomerCreatedEvent implements Event {
  private String name;
  private Money creditLimit;

  public CustomerCreatedEvent() {
  }

  public CustomerCreatedEvent(String name, Money creditLimit) {
    this.name = name;
    this.creditLimit = creditLimit;
  }

  public Money getCreditLimit() {
    return creditLimit;
  }
}
