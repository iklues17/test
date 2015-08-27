package net.chrisrichardson.getataxi.events.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitTestListener {

  @RabbitListener(queues = "crudEvents")
  public void processOrder(SerializedEventEnvelope data) {
    System.out.println("++++++++++++++++++++++++" + data);
  }
}
