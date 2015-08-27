package net.chrisrichardson.getataxi.repositories.mongodb;

import net.chrisrichardson.getataxi.events.SerializedEvent;
import org.springframework.data.annotation.Id;

import java.util.List;

public class EntityWithEvents {

  @Id
  String id;

  String _class;

  List<SerializedEvent> _events;


  public String getId() {
    return id;
  }

  public String get_class() {
    return _class;
  }

  public List<SerializedEvent> get_events() {
    return _events;
  }
}
