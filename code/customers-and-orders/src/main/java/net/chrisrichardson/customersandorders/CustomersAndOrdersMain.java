package net.chrisrichardson.customersandorders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import net.chrisrichardson.getataxi.events.DomainEventHandlerConfig;
import net.chrisrichardson.getataxi.events.DomainEventHandlerRegistrationConfiguration;
import net.chrisrichardson.getataxi.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@Import({SwaggerConfiguration.class, DomainEventHandlerRegistrationConfiguration.class})
public class CustomersAndOrdersMain {

  public static void main(String[] args) {

    SpringApplication.run(CustomersAndOrdersMain.class, args);
  }

  @Bean
  public Predicate<String> swaggerPaths() {
    return regex("/customers.*|/orders.*");
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

  @Bean
  public RestTemplate restTemplate(ObjectMapper objectMapper) {
    RestTemplate rt = new RestTemplate();
    rt.getMessageConverters()
            .stream()
            .filter(MappingJackson2HttpMessageConverter.class::isInstance)
            .forEach(mc -> ((MappingJackson2HttpMessageConverter) mc).setObjectMapper(objectMapper));
    return rt;
  }


  @Bean
  public DomainEventHandlerConfig domainEventHandlerConfig() {
    return new DomainEventHandlerConfig("customersAndOrders");
  }

}
