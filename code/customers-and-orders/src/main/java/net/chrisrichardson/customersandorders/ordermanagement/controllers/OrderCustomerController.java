package net.chrisrichardson.customersandorders.ordermanagement.controllers;

import net.chrisrichardson.customersandorders.commondomain.valueobjects.Money;
import net.chrisrichardson.customersandorders.ordermanagement.services.OrderCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCustomerController {

  private OrderCustomerService orderCustomerService;

  @Autowired
  public OrderCustomerController(OrderCustomerService orderCustomerService) {
    this.orderCustomerService = orderCustomerService;
  }

  @RequestMapping(value="/customers/{customerId}/availablecredit", method= RequestMethod.GET)
  public GetAvailableCreditResponse getAvailableCredit(@PathVariable String customerId) {
    Money availableCredit = orderCustomerService.getAvailableCredit(customerId);
    return new GetAvailableCreditResponse(availableCredit);
  }
}
