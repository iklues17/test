package net.chrisrichardson.getataxi.events;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SerializedEvent {
  private String eventId;
  private String eventType;
  private String eventData;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public SerializedEvent() {
  }

  public SerializedEvent(String eventId, String eventType, String eventData) {
    this.eventId = eventId;
    this.eventType = eventType;
    this.eventData = eventData;
  }

  public String getEventId() {
    return eventId;
  }

  public String getEventType() {
    return eventType;
  }

  public String getEventData() {
    return eventData;
  }
}
