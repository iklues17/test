package net.chrisrichardson.getataxi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.chrisrichardson.getataxi.UtilConfiguration;
import net.chrisrichardson.getataxi.events.mongodb.DomainEventRepository;
import net.chrisrichardson.getataxi.events.rabbitmq.EventPublisherImpl;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.*;

@Configuration
@ImportResource("classpath*:/appctx/*.xml")
@Import(UtilConfiguration.class)
public class UtilEventsConfiguration {

  @Bean
  public DomainEventRepository domainEventRepository() {
    return new DomainEventRepository();
  }

  @Bean
  public EventPublisher eventPublisher() {
    return new EventPublisherImpl();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jsonConverter) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jsonConverter);
    return template;
  }

  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
    Jackson2JsonMessageConverter jmc = new Jackson2JsonMessageConverter();
    jmc.setJsonObjectMapper(objectMapper);
    return jmc;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jsonConverter) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrentConsumers(3);
    factory.setMaxConcurrentConsumers(10);
    factory.setMessageConverter(jsonConverter);
    return factory;
  }
}
