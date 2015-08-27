package net.chrisrichardson.customersandorders.ordermanagement.domain;

import net.chrisrichardson.customersandorders.commondomain.domainevents.CreditCheckApproved;
import net.chrisrichardson.customersandorders.commondomain.domainevents.CreditCheckRejected;
import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

import net.chrisrichardson.getataxi.events.Event;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "orderCustomers")
public class OrderCustomer {

  private String id;
  private long version;

  private Money creditLimit;
  private Map<String, Money> creditReservations = new HashMap<>();

  public OrderCustomer(String id, Money creditLimit) {
    this.id = id;
    this.creditLimit = creditLimit;
  }


  public Money availableCredit() {
    return creditLimit.subtract(creditReservations.values().stream().reduce(Money.ZERO, Money::add));
  }

  public List<Event> reserveCredit(String orderId, Money orderTotal) {
    if (orderTotal.isLessThanOrEqualTo(availableCredit())) {
      creditReservations.put(orderId, orderTotal);
      return Collections.singletonList(new CreditCheckApproved(orderId));
    } else {
      return Collections.singletonList(new CreditCheckRejected(orderId));
    }
  }
}
