package net.chrisrichardson.customersandorders.ordermanagement.services;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;

public interface OrderCustomerService {
  Money getAvailableCredit(String customerId);
}
