package net.chrisrichardson.customersandorders.commondomain.domainevents;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.getataxi.events.Event;

public class OrderCreatedEvent implements Event {
  private Money orderTotal;
  private String customerId;

  public OrderCreatedEvent() {
  }

  public OrderCreatedEvent(String customerId, Money orderTotal) {
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
