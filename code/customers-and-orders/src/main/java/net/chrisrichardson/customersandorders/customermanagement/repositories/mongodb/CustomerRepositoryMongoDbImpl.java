package net.chrisrichardson.customersandorders.customermanagement.repositories.mongodb;

import net.chrisrichardson.customersandorders.customermanagement.domain.Customer;
import net.chrisrichardson.customersandorders.customermanagement.repositories.CustomerRepository;
import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryMongoDbImpl extends AbstractMongoDbRepository<Customer> implements CustomerRepository {

  public CustomerRepositoryMongoDbImpl() {
    super(Customer.class);
  }

}
