package net.chrisrichardson.getataxi.events;



import java.util.List;

public interface EventPublisher {
  void publish(String entityTypeName, String id, List<SerializedEvent> serEvents);
}

