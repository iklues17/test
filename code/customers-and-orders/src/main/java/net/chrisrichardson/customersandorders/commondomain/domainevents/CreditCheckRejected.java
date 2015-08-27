package net.chrisrichardson.customersandorders.commondomain.domainevents;


import net.chrisrichardson.getataxi.events.Event;


public class CreditCheckRejected implements Event {

  private String orderId;

  public CreditCheckRejected() {
  }

  public CreditCheckRejected(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderId() {
    return orderId;
  }
}
