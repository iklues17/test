package net.chrisrichardson.getataxi.events.repositories.mongodb;

import net.chrisrichardson.getataxi.events.mongodb.DomainEventRepository;
import net.chrisrichardson.getataxi.repositories.mongodb.EntityWithEvents;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainEventRepositoryTestConfiguration.class)
@IntegrationTest
public class DomainEventRepositoryTest {

  @Autowired
  private DomainEventRepository domainEventRepository;

  @Test
  public void shouldFindEvents() {
    EntityWithEvents result = domainEventRepository.findEvents(SomeTestEntity.class.getName());
    System.out.println("result=" + result);
  }
}
