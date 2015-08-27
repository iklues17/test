package net.chrisrichardson.customersandorders.customermanagement.domain;

import net.chrisrichardson.customersandorders.commondomain.domainevents.CustomerCreatedEvent;
import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.getataxi.events.EntityAndEvents;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class Customer {

  private String  id;
  private long version;

  private String name;
  private Money creditLimit;

  public Customer(String name, Money creditLimit) {

    this.name = name;
    this.creditLimit = creditLimit;
  }

  public static EntityAndEvents<Customer> make(String name, Money creditLimit) {
    Customer customer = new Customer(name, creditLimit);
    CustomerCreatedEvent event = new CustomerCreatedEvent(name, creditLimit);
    return new EntityAndEvents<Customer>(customer, event);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
