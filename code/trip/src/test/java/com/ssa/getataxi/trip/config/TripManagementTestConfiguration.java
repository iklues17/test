package com.ssa.getataxi.trip.config;

import net.chrisrichardson.getataxi.testutil.events.TestListenerConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import com.ssa.getataxi.trip.repositories.PassengerRepository;
import com.ssa.getataxi.trip.services.TripManagementServiceConfiguration;

import static org.mockito.Mockito.mock;

@Configuration
@Import({TripManagementServiceConfiguration.class,
	TestListenerConfiguration.class})
public class TripManagementTestConfiguration {
	
	@Bean
	@Profile("mockedClientProxies")
	public PassengerRepository passengerRepository(){
		return mock(PassengerRepository.class);
	}

}
