package com.ssa.getataxi.dispatcher.services;

import net.chrisrichardson.getataxi.events.QueueDrainer;
import net.chrisrichardson.getataxi.events.QueueInitializer;
import net.chrisrichardson.getataxi.testutil.events.TestListenerConfiguration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.dispatcher.services.DispatchServiceConfiguration;

@Configuration
@Import({DispatchServiceConfiguration.class,
 TestListenerConfiguration.class})
public class DispatcherServiceIntegrationTestConfiguration {

	@Bean
	public QueueInitializer queueDrainer(AmqpAdmin amqpAdmin) {
		return new QueueDrainer(amqpAdmin);
	}
}
