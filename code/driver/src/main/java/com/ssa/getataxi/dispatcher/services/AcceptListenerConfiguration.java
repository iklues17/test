package com.ssa.getataxi.dispatcher.services;

import net.chrisrichardson.getataxi.testutil.events.DomainEventTestPublisher;
import net.chrisrichardson.getataxi.testutil.events.TestMessageListener;

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

import com.fasterxml.jackson.databind.ObjectMapper;

//@Configuration
//@EnableRabbit
//@EnableAutoConfiguration
public class AcceptListenerConfiguration implements RabbitListenerConfigurer {

	@Autowired
	private AmqpAdmin rabbitAdmin;

	@Autowired
	private Jackson2JsonMessageConverter jsonMessageConverter;

	@Bean
	public TestMessageListener testMessageListener(ObjectMapper objectMapper) {
		return new TestMessageListener(jsonMessageConverter, objectMapper);
	}

	@Bean
	public DomainEventTestPublisher domainEventTestPublisher() {
		return new DomainEventTestPublisher();
	}

	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		Queue queue = rabbitAdmin.declareQueue();
		rabbitAdmin.declareBinding(new Binding(queue.getName(), Binding.DestinationType.QUEUE, "crudEvents", "#", null));
		SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
		endpoint.setId("testMessageListener");
		endpoint.setQueueNames(queue.getName());
		endpoint.setMessageListener(testMessageListener(null));
		registrar.registerEndpoint(endpoint);
	}
}