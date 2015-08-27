package com.ssa.getataxi.trip.repositories;

import net.chrisrichardson.getataxi.events.UtilEventsConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import(UtilEventsConfiguration.class)
public class TripRepositoryConfiguration {

}
