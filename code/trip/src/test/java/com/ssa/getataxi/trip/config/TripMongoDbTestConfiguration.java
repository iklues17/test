package com.ssa.getataxi.trip.config;

import net.chrisrichardson.getataxi.testutil.events.TestListenerConfiguration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.trip.repositories.TripRepositoryConfiguration;

@Configuration
@Import({TestListenerConfiguration.class, TripRepositoryConfiguration.class})
@EnableAutoConfiguration
public class TripMongoDbTestConfiguration {

}
