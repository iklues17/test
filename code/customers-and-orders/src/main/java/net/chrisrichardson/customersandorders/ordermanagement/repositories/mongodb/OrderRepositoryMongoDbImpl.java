package net.chrisrichardson.customersandorders.ordermanagement.repositories.mongodb;


import net.chrisrichardson.customersandorders.ordermanagement.domain.Order;
import net.chrisrichardson.customersandorders.ordermanagement.repositories.OrderRepository;
import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryMongoDbImpl extends AbstractMongoDbRepository<Order> implements OrderRepository {

  public OrderRepositoryMongoDbImpl() {
    super(Order.class);
  }

}
