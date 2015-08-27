package com.ssa.getataxi.dispatcher.services;

import net.chrisrichardson.getataxi.events.DomainEventHandlerConfig;
import net.chrisrichardson.getataxi.events.DomainEventHandlerRegistrationConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.dispatcher.repositories.DispatcherRepositoryConfiguration;
import com.ssa.getataxi.dispatcher.repositories.TripRepository;
import com.ssa.getataxi.driver.repositories.DriverRepository;
import com.ssa.getataxi.driver.repositories.DriverRepositoryConfiguration;

@Configuration
@ComponentScan
@Import({DomainEventHandlerRegistrationConfiguration.class,
	DispatcherRepositoryConfiguration.class,
	DriverRepositoryConfiguration.class})
public class DispatchServiceConfiguration {

	@Bean
	public DomainEventHandlerConfig domainEventHandlerConfig(){
		return new DomainEventHandlerConfig("dispatcher");
	}

//	@Bean
//	DispatcherService dispatcherDomainEventHandlers(
//			TripRepository tripRepository, DriverRepository driverRepository){
//		return new DispatcherServiceImpl(tripRepository, driverRepository);
//	}


}
