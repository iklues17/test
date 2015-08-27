package net.chrisrichardson.getataxi.repositories;


import net.chrisrichardson.getataxi.events.Event;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface Repository<T> {

  // Core methods

  T add(T entity);
  T add(T entity, List<Event> event);

  void update(T entity);
  void update(T entity, List<Event> events);


  T findOne(String id);

  void delete(String id);

  Optional<T> findOneOptional(String id);

  // Convenience methods

  T modify(String id, Function<T, List<Event>> modifier);
}
