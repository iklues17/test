package net.chrisrichardson.customersandorders.ordermanagement.services;

import net.chrisrichardson.customersandorders.commondomain.domainevents.CreditCheckApproved;
import net.chrisrichardson.customersandorders.commondomain.domainevents.CreditCheckRejected;
import net.chrisrichardson.customersandorders.commondomain.domainevents.CustomerCreatedEvent;
import net.chrisrichardson.customersandorders.commondomain.domainevents.OrderCreatedEvent;
import net.chrisrichardson.customersandorders.ordermanagement.domain.Order;
import net.chrisrichardson.customersandorders.ordermanagement.domain.OrderCustomer;
import net.chrisrichardson.customersandorders.ordermanagement.repositories.OrderCustomerRepository;
import net.chrisrichardson.customersandorders.ordermanagement.repositories.OrderRepository;
import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

import net.chrisrichardson.getataxi.events.DomainEventHandler;
import net.chrisrichardson.getataxi.events.EntityAndEvents;
import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.events.EventEnvelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService, DomainEventHandler {

  private final OrderRepository orderRepository;
  private OrderCustomerRepository customerRepository;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, OrderCustomerRepository customerRepository) {
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
  }


  @Override
  public PlaceOrderResult placeOrder(PlaceOrderRequest request) {
    OrderCustomer customer = customerRepository.findOne(request.getCustomerId());
    EntityAndEvents<Order> entityAndEvents = Order.make(request.getCustomerId(), request.getOrderTotal());
    orderRepository.add(entityAndEvents.entity, entityAndEvents.events);
    String orderId = entityAndEvents.entity.getId();
    System.out.println("Created order=" + orderId);
    return new PlaceOrderResult(orderId);
  }

  @Override
  public Order getOrder(String orderId) {
    return orderRepository.findOne(orderId);
  }

  public void handleOrderCreatedEvent(EventEnvelope<OrderCreatedEvent> eventEnvelope) {
    String orderId = eventEnvelope.getEntityId();
    Money orderTotal = eventEnvelope.getEvent().getOrderTotal();
    String customerId = eventEnvelope.getEvent().getCustomerId();

    System.out.println("reserving credit for=" + orderId);

    OrderCustomer customer = customerRepository.findOne(customerId);
    List<Event> events = customer.reserveCredit(orderId, orderTotal);

    customerRepository.update(customer, events);
  }

  public void handleCreditCheckApproved(EventEnvelope<CreditCheckApproved> eventEnvelope) {
    String orderId = eventEnvelope.getEvent().getOrderId();
    System.out.println("handleCreditCheckApproved=" + orderId);
    Order order = orderRepository.findOne(orderId);
    List<Event> events = order.accept();
    orderRepository.update(order, events);
  }

  public void handleCreditCheckRejected(EventEnvelope<CreditCheckRejected> eventEnvelope) {
    String orderId = eventEnvelope.getEvent().getOrderId();
    System.out.println("handleCreditCheckRejected=" + orderId);
    Order order = orderRepository.findOne(orderId);
    List<Event> events = order.reject();
    orderRepository.update(order, events);
  }

  public void handleCustomerCreatedEvent(EventEnvelope<CustomerCreatedEvent> eventEnvelope) {
    OrderCustomer orderCustomer = new OrderCustomer(eventEnvelope.getEntityId(), eventEnvelope.getEvent().getCreditLimit());
    customerRepository.add(orderCustomer);
  }


  // TODO - customer needs to pay for order, somehow
}
