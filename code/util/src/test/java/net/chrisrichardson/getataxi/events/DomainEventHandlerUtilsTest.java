package net.chrisrichardson.getataxi.events;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DomainEventHandlerUtilsTest {

  class TripCreatedByDispatcher implements Event {

  }

  class  TripCreatedEvent implements Event {

  }

  class ExampleHandler1 implements DomainEventHandler {
    public void handleTripCreatedByDispatcher(EventEnvelope<TripCreatedByDispatcher> eventEnvelope) {}

  }

  class ExampleHandler2 implements DomainEventHandler {


    public void handleTripCreatedEvent(EventEnvelope<TripCreatedEvent> eventEnvelope) {


    }
  }

  @Test
  public void classNamesShouldWork() {
    Set<Class<?>> eventClassNames = DomainEventHandlerUtils.eventClassesFromHandlerClasses(Arrays.asList(ExampleHandler1.class, ExampleHandler2.class));

    Set<Class<?>> expectedClasses = new HashSet<>();
    expectedClasses.add(TripCreatedByDispatcher.class);
    expectedClasses.add(TripCreatedEvent.class);

    Assert.assertEquals(expectedClasses, eventClassNames);
  }

}