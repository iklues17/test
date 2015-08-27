package com.ssa.getataxi.trip;

import static springfox.documentation.builders.PathSelectors.regex;
import net.chrisrichardson.getataxi.events.DomainEventHandlerRegistrationConfiguration;
import net.chrisrichardson.getataxi.swagger.SwaggerConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

import com.google.common.base.Predicate;

@SpringBootApplication
@Import({SwaggerConfiguration.class, DomainEventHandlerRegistrationConfiguration.class})
public class TripManagementMain {

  public static void main(String[] args) {

    SpringApplication.run(TripManagementMain.class, args);
  }

  @Bean
  public Predicate<String> swaggerPaths() {
    return regex("/trips.*");
  }

  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Customers and Orders API")
            .description("The customers and orders API")
            .contact("Chris Richardson")
            .license("Apache License, Version 2.0")
            .version("1.0")
            .build();
  }


//  @Bean
//  public DomainEventHandlerConfig domainEventHandlerConfig() {
//    return new DomainEventHandlerConfig("tripStarted");
//  }

}
