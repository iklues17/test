package com.ssa.getataxi.dispatcher;

import static org.junit.Assert.assertEquals;
import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.events.EventEnvelope;
import net.chrisrichardson.getataxi.events.QueueInitializer;
import net.chrisrichardson.getataxi.testutil.events.DomainEventTestPublisher;
import net.chrisrichardson.getataxi.testutil.events.TestMessageListener;
import net.chrisrichardson.getataxi.util.misc.IdGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssa.getataxi.commondomain.events.DriverAcceptedTripEvent;
import com.ssa.getataxi.commondomain.events.TripCreatedEvent;
import com.ssa.getataxi.commondomain.events.TripOfferedToDriver;
import com.ssa.getataxi.commondomain.events.TripStarted;
import com.ssa.getataxi.dispatcher.services.DispatcherServiceIntegrationTestConfiguration;
import com.ssa.getataxi.driver.DriverMother;
import com.ssa.getataxi.driver.domain.Driver;
import com.ssa.getataxi.driver.repositories.DriverRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =
 DispatcherServiceIntegrationTestConfiguration.class)
@IntegrationTest
public class DispatcherServiceIntegrationTest {

  {

//    Assert.assertNotNull(System.getenv("SPRING_DATA_MONGODB_URI"));
//    Assert.assertNotNull(System.getenv("SPRING_RABBITMQ_HOST"));
  }

  @Autowired
  private DomainEventTestPublisher domainEventTestPublisher;

  @Autowired
  private TestMessageListener testMessageListener;

  @Autowired
  private DriverRepository driverRepository;

  @Autowired(required=false)
  private QueueInitializer queueInitializer;

  @Test
  public void shouldHandleTripCreatedEvent() {

    Driver driver = DriverMother.makeDriver();

    driverRepository.add(driver);

    String driverId = driver.getId();

    Location pickupLocation = driver.getLocation();
    String passengerId = IdGenerator.generateId();
    Event tripCreatedEvent = new TripCreatedEvent(passengerId, pickupLocation);
    String tripId = IdGenerator.generateId();

    domainEventTestPublisher.publishDomainEvent(
    		tripId, TripOfferedToDriver.class.getName(), tripCreatedEvent);

    System.out.println("");
    EventEnvelope<TripOfferedToDriver> offeredEvent = testMessageListener.
            assertMessagePublished(tripId, TripOfferedToDriver.class);
//
//    assertEquals(tripId, offeredEvent.getEntityId());
//    assertEquals(driverId, offeredEvent.getEvent().getDriverId());

//    domainEventTestPublisher.publishDomainEvent(
//            driverId, TripStarted.class.getName(),
//            new DriverAcceptedTripEvent(tripId));
//
//    EventEnvelope<TripStarted> tripStartedEvent =
//            testMessageListener.assertMessagePublished(tripId, TripStarted.class);
//
//    assertEquals(tripId, tripStartedEvent.getEntityId());
//    assertEquals(driverId, tripStartedEvent.getEvent().getDriverId());

  }


}