package net.chrisrichardson.getataxi.events;

import net.chrisrichardson.getataxi.events.QueueInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;

public class QueueDrainer implements QueueInitializer {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private final AmqpAdmin amqpAdmin;

  public QueueDrainer(AmqpAdmin amqpAdmin) {
    this.amqpAdmin = amqpAdmin;
  }

  @Override
  public void initializeQueue(String queueName) {
    logger.info("Purging queue: {}", queueName);
    amqpAdmin.purgeQueue(queueName, true);
  }
}
