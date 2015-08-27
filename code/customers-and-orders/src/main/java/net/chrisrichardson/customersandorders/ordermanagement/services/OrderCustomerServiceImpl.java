package net.chrisrichardson.customersandorders.ordermanagement.services;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.customersandorders.ordermanagement.repositories.OrderCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCustomerServiceImpl implements OrderCustomerService {

  private OrderCustomerRepository orderCustomerRepository;

  @Autowired
  public OrderCustomerServiceImpl(OrderCustomerRepository orderCustomerRepository) {
    this.orderCustomerRepository = orderCustomerRepository;
  }


  @Override
  public Money getAvailableCredit(String customerId) {
    return orderCustomerRepository.findOne(customerId).availableCredit();
  }
}
