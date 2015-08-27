package net.chrisrichardson.customersandorders.customermanagement.services;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.customersandorders.customermanagement.domain.Customer;

public interface CustomerManagementService {
  public net.chrisrichardson.customersandorders.customermanagement.domain.Customer createCustomer(String name, Money creditLimit);

  public CustomerInfo getCustomerInfo(String customerId);

  Customer getCustomer(String customerId);
}
