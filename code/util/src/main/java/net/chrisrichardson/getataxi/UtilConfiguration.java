package net.chrisrichardson.getataxi;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.chrisrichardson.getataxi.repositories.mongodb.DriverAfterConvertEventListener;
import net.chrisrichardson.getataxi.util.architecture.SystemArchitecture;
import net.chrisrichardson.getataxi.util.logging.LoggingAspect;
import net.chrisrichardson.getataxi.util.optimisticlocking.OptimisticLockingRetryAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class UtilConfiguration {

  @Bean
  public SystemArchitecture systemArchitecture() {
    return new SystemArchitecture();
  }

  @Bean
  public LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }
  @Bean
  public OptimisticLockingRetryAspect optimisticLockingRetryAspect() {
    return new OptimisticLockingRetryAspect();
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    ObjectMapper om = new ObjectMapper();

    GetATaxiObjectMapperConfigurer.configureObjectMapper(om);

    return om;
  }

  @Bean
  public DriverAfterConvertEventListener driverAfterConvertEventListener() {
    return new DriverAfterConvertEventListener();
  }

}
