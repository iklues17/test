package net.chrisrichardson.customersandorders.customermanagement.controllers;

import net.chrisrichardson.customersandorders.customermanagement.domain.Customer;
import net.chrisrichardson.customersandorders.customermanagement.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

  private CustomerManagementService customerService;

  @Autowired
  public CustomerController(CustomerManagementService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(value="/customers", method = RequestMethod.POST)
  public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest request) {
    Customer customer = customerService.createCustomer(request.getName(), request.getCreditLimitAsMoney());
    return new CreateCustomerResponse(customer.getId());
  }

  @RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET)
  public GetCustomerResponse createCustomer(@PathVariable String customerId) {
    Customer customer = customerService.getCustomer(customerId);
    return new GetCustomerResponse(customer.getName());
  }
}
