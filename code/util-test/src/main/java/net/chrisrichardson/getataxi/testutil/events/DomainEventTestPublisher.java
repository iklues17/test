package net.chrisrichardson.getataxi.testutil.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.events.SerializedEvent;
import net.chrisrichardson.getataxi.events.rabbitmq.SerializedEventEnvelope;
import net.chrisrichardson.getataxi.util.misc.IdGenerator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DomainEventTestPublisher {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void publishDomainEvent(String entityId, String entityTypeName, Event event) {
    String exchangeName = "crudEvents";
    String eventId = IdGenerator.generateId();
    String eventData;
    try {
      eventData = objectMapper.writeValueAsString(event);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    SerializedEvent serEvent = new SerializedEvent(eventId, event.getClass().getName(), eventData);


    rabbitTemplate.convertAndSend(exchangeName, serEvent.getEventType(), new SerializedEventEnvelope(entityTypeName, entityId, serEvent));
  }
}
