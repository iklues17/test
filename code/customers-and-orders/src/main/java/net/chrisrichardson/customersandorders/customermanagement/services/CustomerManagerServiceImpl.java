package net.chrisrichardson.customersandorders.customermanagement.services;

import net.chrisrichardson.customersandorders.customermanagement.domain.Customer;
import net.chrisrichardson.customersandorders.customermanagement.repositories.CustomerRepository;
import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.getataxi.events.DomainEventHandler;
import net.chrisrichardson.getataxi.events.EntityAndEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerManagerServiceImpl implements CustomerManagementService, DomainEventHandler {


  private CustomerRepository customerRepository;

  @Autowired
  public CustomerManagerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer createCustomer(String name, Money creditLimit) {
    EntityAndEvents<Customer> eae = Customer.make(name, creditLimit);
    return customerRepository.add(eae.entity, eae.events);
  }

  @Override
  public CustomerInfo getCustomerInfo(String customerId) {
    return null;
  }

  @Override
  public Customer getCustomer(String customerId) {
    return customerRepository.findOne(customerId);
  }

}
