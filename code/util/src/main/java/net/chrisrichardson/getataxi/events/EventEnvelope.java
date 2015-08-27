package net.chrisrichardson.getataxi.events;




public class EventEnvelope<T extends Event> {

  private String id;
  private T event;

  public EventEnvelope(String id, T event) {
    this.id = id;
    this.event = event;
  }

  public String getEntityId() {
    return id;
  }

  public T getEvent() {
    return event;
  }
}
