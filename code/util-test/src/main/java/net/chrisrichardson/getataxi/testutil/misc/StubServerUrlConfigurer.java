package net.chrisrichardson.getataxi.testutil.misc;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class StubServerUrlConfigurer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {

    Map<String, Object> map = new HashMap<>();
    for (String url : getPropertyNames()) {
      map.put(url, "http://localhost:" + AvailablePortUtil.findPort());
    }
    MapPropertySource mps = new MapPropertySource("StubServerUrlConfigurer Source", map);
    applicationContext.getEnvironment().getPropertySources().addFirst(mps);
  }

  protected abstract Set<String> getPropertyNames();
}
