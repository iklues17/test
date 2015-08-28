package net.chrisrichardson.getataxi.apigateway;

import net.chrisrichardson.getataxi.eureka.EurekaConfiguration;
import net.chrisrichardson.getataxi.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Import({EurekaConfiguration.class})
public class ApiGatewayMain {


  @Bean
  public ProxyClient proxyClient(RestTemplate restTemplate) {
    Map<String, String> routes = new HashMap<>();
    routes.put("passengers", "PASSENGER-MANAGEMENT");
    return new ProxyClientImpl(restTemplate, routes);
  }
  public static void main(String[] args) {
    SpringApplication.run(ApiGatewayMain.class, args);
  }
}
