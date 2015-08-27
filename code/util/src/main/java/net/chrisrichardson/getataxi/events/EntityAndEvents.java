package net.chrisrichardson.getataxi.events;

import net.chrisrichardson.getataxi.events.Event;

import java.util.Arrays;
import java.util.List;

public class EntityAndEvents<T> {
  public List<Event> events;
  public T entity;

  public EntityAndEvents(T entity, List<Event> events) {
    this.entity = entity;
    this.events = events;
  }

  public EntityAndEvents(T entity, Event... events) {
    this.entity = entity;
    this.events = Arrays.asList(events);
  }
}
