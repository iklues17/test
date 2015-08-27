package net.chrisrichardson.customersandorders.commondomain.domainevents;


import net.chrisrichardson.getataxi.events.Event;


public class CreditCheckApproved implements Event {
  private String orderId;

  public CreditCheckApproved() {
  }

  public CreditCheckApproved(String orderId) {
    this.orderId = orderId;
  }

  public String getOrderId() {
    return orderId;
  }
}
