package net.chrisrichardson.getataxi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.chrisrichardson.getataxi.events.rabbitmq.SerializedEventEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainEventListener implements MessageListener {
  private Logger logger = LoggerFactory.getLogger(getClass());

  private final List<DomainEventHandler> domainEventHandlers;
  private final Jackson2JsonMessageConverter jsonMessageConverter;
  private final ObjectMapper objectMapper;

  private Map<String, List<EventHandlerMethod>>  eventHandlersForEvent = new HashMap<>();

  public DomainEventListener(List<DomainEventHandler> domainEventHandlers,
                             Jackson2JsonMessageConverter jsonMessageConverter,
                             ObjectMapper objectMapper) {
    this.domainEventHandlers = domainEventHandlers;
    this.jsonMessageConverter = jsonMessageConverter;
    this.eventHandlersForEvent = DomainEventHandlerUtils.makeEventHandlersForEventMap(domainEventHandlers);
    this.objectMapper = objectMapper;
  }

  @Override
  public void onMessage(Message message) {
    SerializedEventEnvelope serializedEventEnvelope = (SerializedEventEnvelope) jsonMessageConverter.fromMessage(message);
    logger.debug("Got message={}", serializedEventEnvelope);

    EventEnvelope eventEnvelope = makeEventEnvelope(serializedEventEnvelope);

    for (EventHandlerMethod eh : eventHandlersForEvent.get(serializedEventEnvelope.getSerEvent().getEventType())) {
        try {
          eh.handle(eventEnvelope);
        } catch (Throwable t) {
           logger.error("Exception thrown by event handler:" + eventEnvelope, t);
        }
    }
  }

  private EventEnvelope makeEventEnvelope(SerializedEventEnvelope serializedEventEnvelope) {
    Class<Event> eventClass = null;
    try {
      eventClass = (Class<Event>) Class.forName(serializedEventEnvelope.getSerEvent().getEventType());
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    Event event = null;
    try {
      event = objectMapper.readValue(serializedEventEnvelope.getSerEvent().getEventData(), eventClass);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return new EventEnvelope(serializedEventEnvelope.getId(), event);
  }
}
