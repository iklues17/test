package com.ssa.getataxi.dispatcher.services;

import net.chrisrichardson.getataxi.events.DomainEventHandlerConfig;
import net.chrisrichardson.getataxi.events.DomainEventHandlerRegistrationConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.dispatcher.repositories.DispatcherRepositoryConfiguration;
import com.ssa.getataxi.dispatcher.repositories.DriverRepository;
import com.ssa.getataxi.dispatcher.repositories.TripRepository;

@Configuration
@Import({DomainEventHandlerRegistrationConfiguration.class,
	DispatcherRepositoryConfiguration.class})
public class DispatcherServiceConfiguration {

	@Bean
	public DomainEventHandlerConfig domainEventHandlerConfig(){
		return new DomainEventHandlerConfig("dispatcher");
	}

//	@Bean
//	DispatcherService dispatcherDomainEventHandlers(
//			TripRepository tripRepository, DriverRepository driverRepository){
//		return new DispatcherService(tripRepository, driverRepository);
//	}


}
