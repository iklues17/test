package net.chrisrichardson.getataxi.events.repositories.mongodb;

import net.chrisrichardson.getataxi.events.UtilEventsConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(UtilEventsConfiguration.class)
@EnableAutoConfiguration
public class DomainEventRepositoryTestConfiguration {
}
