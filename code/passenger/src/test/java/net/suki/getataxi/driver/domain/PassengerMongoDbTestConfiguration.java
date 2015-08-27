package net.suki.getataxi.driver.domain;

import net.chrisrichardson.getataxi.testutil.events.TestListenerConfiguration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestListenerConfiguration.class,
	PassengerRepositoryConfiguration.class})
@EnableAutoConfiguration
public class PassengerMongoDbTestConfiguration {
	
}
