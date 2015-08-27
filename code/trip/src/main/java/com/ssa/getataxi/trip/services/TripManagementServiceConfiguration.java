package com.ssa.getataxi.trip.services;

import net.chrisrichardson.getataxi.events.DomainEventHandlerConfig;
import net.chrisrichardson.getataxi.events.DomainEventHandlerRegistrationConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.trip.repositories.TripRepositoryConfiguration;

@Configuration
@Import({DomainEventHandlerRegistrationConfiguration.class, TripRepositoryConfiguration.class})
public class TripManagementServiceConfiguration {

	@Bean
	public DomainEventHandlerConfig domainEventHandlerConfig(){
		return new DomainEventHandlerConfig("trip");
	}
	
}
