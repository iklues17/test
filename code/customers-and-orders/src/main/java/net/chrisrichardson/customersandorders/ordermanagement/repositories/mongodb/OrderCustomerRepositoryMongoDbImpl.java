package net.chrisrichardson.customersandorders.ordermanagement.repositories.mongodb;


import net.chrisrichardson.customersandorders.ordermanagement.domain.OrderCustomer;
import net.chrisrichardson.customersandorders.ordermanagement.repositories.OrderCustomerRepository;
import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCustomerRepositoryMongoDbImpl extends AbstractMongoDbRepository<OrderCustomer> implements OrderCustomerRepository {

  public OrderCustomerRepositoryMongoDbImpl() {
    super(OrderCustomer.class);
  }

}
