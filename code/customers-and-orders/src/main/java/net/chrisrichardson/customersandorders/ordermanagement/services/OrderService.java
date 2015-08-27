package net.chrisrichardson.customersandorders.ordermanagement.services;

import net.chrisrichardson.customersandorders.ordermanagement.domain.Order;

public interface OrderService {

  public PlaceOrderResult placeOrder(PlaceOrderRequest request);

  Order getOrder(String orderId);
}
