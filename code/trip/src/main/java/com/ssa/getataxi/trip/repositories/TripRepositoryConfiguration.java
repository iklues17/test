package com.ssa.getataxi.trip.repositories;

import net.chrisrichardson.getataxi.events.UtilEventsConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan
@Import(UtilEventsConfiguration.class)
public class TripRepositoryConfiguration {


	  @Bean
	  public RestTemplate restTemplate(ObjectMapper objectMapper) {
	    RestTemplate rt = new RestTemplate();
	    rt.getMessageConverters()
	            .stream()
	            .filter(MappingJackson2HttpMessageConverter.class::isInstance)
	            .forEach(mc -> ((MappingJackson2HttpMessageConverter) mc).setObjectMapper(objectMapper));
	    return rt;
	  }
}
