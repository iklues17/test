package net.chrisrichardson.getataxi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableRabbit
@EnableAutoConfiguration
@Import(UtilEventsConfiguration.class)
public class DomainEventHandlerRegistrationConfiguration implements RabbitListenerConfigurer {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired(required=false)
  private List<DomainEventHandler> domainEventHandlers = Collections.emptyList();

  @Autowired
  private AmqpAdmin rabbitAdmin;

  @Autowired
  private Jackson2JsonMessageConverter jsonMessageConverter;

  @Autowired(required=false)
  private DomainEventHandlerConfig domainEventHandlerConfig;

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  public DomainEventListener domainEventListener() {
    return new DomainEventListener(domainEventHandlers, jsonMessageConverter, objectMapper);
  }

  @Autowired(required=false)
  private QueueInitializer queueInitializer;

  @Override
  public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
    if (domainEventHandlers.isEmpty()) {
      logger.info("No Domain Event Handlers!");
      return;
    } else
      logger.info("have this many event handlers: {}", domainEventHandlers.size());

    Assert.notNull(domainEventHandlerConfig, "You must define a DomainEventHandlerConfig bean");

    String queueName = domainEventHandlerConfig.getName();
    rabbitAdmin.declareQueue(new Queue(queueName, true));

    if (queueInitializer != null)
      queueInitializer.initializeQueue(queueName);
    for (Class<?> domainEventClass : DomainEventHandlerUtils.eventClassesFromHandlers(domainEventHandlers)) {
      rabbitAdmin.declareBinding(new Binding(queueName, Binding.DestinationType.QUEUE, "crudEvents", domainEventClass.getName(), null));
    }

    SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
    endpoint.setId(queueName);
    endpoint.setQueueNames(queueName);
    endpoint.setMessageListener(domainEventListener());

    registrar.registerEndpoint(endpoint);
  }
}
