package net.chrisrichardson.getataxi.events.rabbitmq;


import net.chrisrichardson.getataxi.events.EventPublisher;
import net.chrisrichardson.getataxi.events.SerializedEvent;
import net.chrisrichardson.getataxi.events.mongodb.DomainEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Component
public class EventPublisherImpl implements EventPublisher {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  DomainEventRepository domainEventRepository;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @PostConstruct
  public void recovery() {
    logger.info("Recovering.....");
    /*
    EntityWithEvents entityWithEvent;
    while ((entityWithEvent = domainEventPump.findEvents()) != null) {
      publish(entityWithEvent.get_class(),
              new String(entityWithEvent.getId()),
              Collections.singletonList(entityWithEvent.get_events().get(0)));
    }
    */
    logger.info("Recovered.....");
  }

  @Override
  public void publish(String entityTypeName, String id, List<SerializedEvent> serEvents) {
    logger.debug("publish() called with: " + Arrays.asList(entityTypeName, id, serEvents));


    for (SerializedEvent serEvent : serEvents) {

      String exchangeName = "crudEvents";

      rabbitTemplate.convertAndSend(exchangeName, serEvent.getEventType(), new SerializedEventEnvelope(entityTypeName, id, serEvent));

      domainEventRepository.deleteEvent(entityTypeName, id, serEvent);

      logger.debug("Deleted event");
    }
  }

}
