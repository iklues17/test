package net.chrisrichardson.getataxi.repositories.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import net.chrisrichardson.getataxi.events.SerializedEvent;
import net.chrisrichardson.getataxi.events.EventPublisher;

import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.repositories.Repository;

import net.chrisrichardson.getataxi.util.misc.IdGenerator;
import org.apache.commons.lang.NotImplementedException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public abstract class AbstractMongoDbRepository<T> implements Repository<T> {
  private Logger logger = LoggerFactory.getLogger(getClass());

  private Class<T> entityClass;
  private Field idField;
  private Field versionField;

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  protected MongoTemplate mongoTemplate;

  @Autowired
  private MappingMongoConverter mongoMappingConverter;

  @Autowired
  private ObjectMapper objectMapper;

  public AbstractMongoDbRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
    this.idField = ReflectionUtils.findField(entityClass, "id");
    Assert.notNull(this.idField, "needs an id field: " + entityClass.getName());
    Assert.isTrue(String.class.isAssignableFrom(this.idField.getType()), "needs an id field of type String: " + entityClass.getName());
    idField.setAccessible(true);
    this.versionField = ReflectionUtils.findField(entityClass, "version");
    Assert.notNull(this.versionField, "needs a version field: " + entityClass.getName());
    versionField.setAccessible(true);
  }

  private String collectionName() {
    return mongoMappingConverter.getMappingContext().getPersistentEntity(entityClass).getCollection();
  }



  private String getId(T entity) {
    try {
      return (String) idField.get(entity);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private void setId(T entity, String id) {
    try {
      idField.set(entity, id);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private long getVersion(T entity) {
    try {
      return (Long) versionField.get(entity);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private void setVersion(T entity, long newVersion) {
    try {
      versionField.set(entity, newVersion);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }

  }

  //@Override
  public T add(T entity, Event... events) {
    // TODO - this should not overwrite an existing doc

    List<SerializedEvent> serEvents = serializeEvents(events);
    BasicDBList dbList = mapEvents(serEvents);

    maybePresave(entity);

    DBObject dbObject = new BasicDBObject();
    mongoMappingConverter.write(entity, dbObject);
    dbObject.put("_events", dbList);
    logger.debug("dbobject={}", dbObject);
    mongoTemplate.insert(dbObject, collectionName());
    if (getId(entity) == null) {
      setId(entity, ((ObjectId) dbObject.get("_id")).toString());
    }
    eventPublisher.publish(entity.getClass().getName(), getId(entity), serEvents);
    return entity;
  }

  private void maybePresave(T entity) {
    if (entity instanceof PreSaveListener) {
      ((PreSaveListener) entity).preSave();
    }
  }

  private BasicDBList mapEvents(List<SerializedEvent> events) {
    BasicDBList list = new BasicDBList();
    list.addAll(mapEventsToArray(events));
    return list;
  }


  private List<DBObject> mapEventsToArray(List<SerializedEvent> events) {
    return events.stream().map(this::mapEvent).collect(Collectors.toList());
  }

  private List<SerializedEvent> serializeEvents(Event[] events) {
    return Arrays.stream(events).map(this::serializeEvent).collect(Collectors.<SerializedEvent>toList());
  }

  private SerializedEvent serializeEvent(Event event) {
    try {
      return new SerializedEvent(IdGenerator.generateId(), event.getClass().getName(), objectMapper.writeValueAsString(event));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
  private DBObject mapEvent(SerializedEvent event) {
    DBObject ev = new BasicDBObject();
    ev.put("eventId", event.getEventId());
    ev.put("eventType", event.getEventType());
    ev.put("eventData", event.getEventData());
    return ev;
  }

//  @Override
  public T update(T entity, Event... events) {
    List<SerializedEvent> serEvents = serializeEvents(events);
    Object[] eventsAsArray = mapEventsToArray(serEvents).toArray();

    maybePresave(entity);

    long expectedVersion = getVersion(entity);
    setVersion(entity, expectedVersion + 1);

    DBObject dbObject = new BasicDBObject();
    mongoMappingConverter.write(entity, dbObject);
    Update update = new Update();
    dbObject.keySet().stream().filter(key -> !key.startsWith("_")).forEach(key -> update.set(key, dbObject.get(key)));
    update.pushAll("_events", eventsAsArray);

    Query q = query(where("_id").is(getId(entity)).and("version").is(expectedVersion));
    WriteResult wr = mongoTemplate.updateFirst(q, update, entityClass);

    if (wr.getN() == 0)
      throw new OptimisticLockingFailureException("Nothing updated");
    else if (wr.getN() != 1)
      throw new RuntimeException("UpdateFirst returned unexpected value: " + wr.getN());

    eventPublisher.publish(entity.getClass().getName(), getId(entity), serEvents);
    return entity;
  }

  @Override
  public T findOne(String id) {
    Assert.notNull(id, "key can't be null");
    return findOneOptional(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
  }

  @Override
  public Optional<T> findOneOptional(String id) {
    return Optional.ofNullable(mongoTemplate.findOne(query(where("_id").is(id)), entityClass));
  }

  @Override
  public void delete(String id) {
    throw new NotImplementedException();
  }

  @Override
  public T add(T entity, List<Event> events) {
    Event[] eventsArray = new Event[events.size()];
    events.toArray(eventsArray);
    return add(entity, eventsArray);
  }

  @Override
  public void update(T entity, List<Event> events) {
    Event[] eventsArray = new Event[events.size()];
    events.toArray(eventsArray);
    update(entity, eventsArray);
  }

  @Override
  public T add(T entity) {
    return add(entity, Collections.emptyList());
  }

  @Override
  public void update(T entity) {
    update(entity, Collections.emptyList());
  }

  @Override
  public T modify(String id, Function<T, List<Event>> modifier) {
    T entity = findOne(id);
    List<Event> events = modifier.apply(entity);
    update(entity, events);
    return entity;
  }
}
