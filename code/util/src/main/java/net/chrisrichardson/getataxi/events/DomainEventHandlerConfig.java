package net.chrisrichardson.getataxi.events;


public class DomainEventHandlerConfig {

  private String name;

  public DomainEventHandlerConfig(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
