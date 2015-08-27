package com.ssa.getataxi.driver.domain;

import net.chrisrichardson.getataxi.testutil.events.TestListenerConfiguration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.passenger.repositories.PassengerRepositoryConfiguration;

@Configuration
@Import({TestListenerConfiguration.class,
	PassengerRepositoryConfiguration.class})
@EnableAutoConfiguration
public class PassengerMongoDbTestConfiguration {
	
}
