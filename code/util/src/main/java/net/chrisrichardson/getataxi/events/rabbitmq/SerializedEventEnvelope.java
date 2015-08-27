package net.chrisrichardson.getataxi.events.rabbitmq;


import net.chrisrichardson.getataxi.events.SerializedEvent;


public class SerializedEventEnvelope {
  private String entityTypeName;
  private String id;
  private SerializedEvent serEvent;

  public SerializedEventEnvelope() {
  }

  @Override
  public String toString() {
    return "SerializedEventEnvelope{" +
            "entityTypeName='" + entityTypeName + '\'' +
            ", id=" + id +
            ", serEvent=" + serEvent +
            '}';
  }

  public SerializedEventEnvelope(String entityTypeName, String id, SerializedEvent serEvent) {
    this.entityTypeName = entityTypeName;
    this.id = id;
    this.serEvent = serEvent;
  }

  public String getEntityTypeName() {
    return entityTypeName;
  }

  public String getId() {
    return id;
  }

  public SerializedEvent getSerEvent() {
    return serEvent;
  }
}
