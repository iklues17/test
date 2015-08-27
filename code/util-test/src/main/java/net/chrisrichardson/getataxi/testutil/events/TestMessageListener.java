package net.chrisrichardson.getataxi.testutil.events;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.events.EventEnvelope;
import net.chrisrichardson.getataxi.events.rabbitmq.SerializedEventEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import org.junit.Assert;


public class TestMessageListener implements MessageListener {
  private Logger logger = LoggerFactory.getLogger(getClass());

  private final Jackson2JsonMessageConverter jsonMessageConverter;
  private final ObjectMapper objectMapper;
  private BlockingQueue<SerializedEventEnvelope> messages = new LinkedBlockingQueue<>();

  public TestMessageListener(Jackson2JsonMessageConverter jsonMessageConverter, ObjectMapper objectMapper) {
    this.jsonMessageConverter = jsonMessageConverter;
    this.objectMapper = objectMapper;
  }

  public List<SerializedEventEnvelope> getMessages() {
    List<SerializedEventEnvelope> result = new ArrayList<>();
    result.addAll(messages);
    return result;
  }

  @Override
  public void onMessage(Message message) {
    try {
      SerializedEventEnvelope serializedEventEnvelope = (SerializedEventEnvelope) jsonMessageConverter.fromMessage(message);
      logger.info("Got message=" + serializedEventEnvelope);
      messages.put(serializedEventEnvelope);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public <T extends Event> EventEnvelope<T> assertMessagePublished(String entityId, Class<T> eventType) {
    Optional<Optional<SerializedEventEnvelope>> eventFound = Stream.generate(() -> {
      List<SerializedEventEnvelope> messages = getMessages();
      Optional<SerializedEventEnvelope> found =
              messages.stream().filter(m ->
                      m.getSerEvent().getEventType().equals(eventType.getName()) &&
                              m.getId().equals(entityId)).findFirst();
      if (!found.isPresent()) {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
      return found;
    }).limit(10).filter(Optional::isPresent).findFirst();

    Assert.assertTrue("should find event of type " + eventType.getName() + " for entityId " + entityId, eventFound.isPresent());

    return makeEventEnvelope(eventFound.get().get());
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
