package net.chrisrichardson.customersandorders.ordermanagement.domain;


import net.chrisrichardson.customersandorders.commondomain.domainevents.OrderAcceptedEvent;
import net.chrisrichardson.customersandorders.commondomain.domainevents.OrderCreatedEvent;
import net.chrisrichardson.customersandorders.commondomain.domainevents.OrderRejectedEvent;
import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.getataxi.events.EntityAndEvents;
import net.chrisrichardson.getataxi.events.Event;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "orders")
public class Order {
  private String id;
  private long version;

  public OrderStatus getStatus() {
    return status;
  }


  private OrderStatus status;
  private String customerId;
  private Money orderTotal;

  public Order(String customerId, Money orderTotal) {

    this.customerId = customerId;
    this.orderTotal = orderTotal;
    this.status = OrderStatus.CREATED;
  }

  public String getId() {
    return id;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public static EntityAndEvents<Order> make(String customerId, Money orderTotal) {
    Order order = new Order(customerId, orderTotal);
    List<Event> events = Collections.singletonList(new OrderCreatedEvent(customerId, orderTotal));
    return new EntityAndEvents<>(order, events);
  }

  public List<Event> accept() {
    this.status = OrderStatus.OPEN;
    return Collections.singletonList(new OrderAcceptedEvent());
  }

  public List<Event> reject() {
    this.status = OrderStatus.CANCELLED_NO_CREDIT;
    return Collections.singletonList(new OrderRejectedEvent());
  }
}
