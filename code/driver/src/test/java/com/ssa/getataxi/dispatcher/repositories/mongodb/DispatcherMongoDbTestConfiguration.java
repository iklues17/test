package com.ssa.getataxi.dispatcher.repositories.mongodb;

import net.chrisrichardson.getataxi.testutil.events.TestListenerConfiguration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ssa.getataxi.dispatcher.repositories.DispatcherRepositoryConfiguration;

@Configuration
@Import({TestListenerConfiguration.class,
 DispatcherRepositoryConfiguration.class})
@EnableAutoConfiguration
public class DispatcherMongoDbTestConfiguration {

}
