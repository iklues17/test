package net.chrisrichardson.getataxi.eureka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableEurekaClient
@Profile("enableEureka")
class EurekaClientConfiguration {

  class DummyClass {}

  @Bean
  public DummyClass restTemplateInitializer(RestTemplate restTemplate, ObjectMapper objectMapper ) {
    restTemplate.getMessageConverters()
            .stream()
            .filter(MappingJackson2HttpMessageConverter.class::isInstance)
            .forEach(mc -> ((MappingJackson2HttpMessageConverter) mc).setObjectMapper(objectMapper));
    return new DummyClass();
  }

}