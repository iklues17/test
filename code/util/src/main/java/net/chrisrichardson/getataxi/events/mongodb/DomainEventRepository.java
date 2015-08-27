package net.chrisrichardson.getataxi.events.mongodb;

import com.mongodb.WriteResult;

import net.chrisrichardson.getataxi.events.SerializedEvent;
import net.chrisrichardson.getataxi.repositories.mongodb.EntityWithEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class DomainEventRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private MappingMongoConverter mongoMappingConverter;

  private String collectionName(Class<?> entityClass) {
    return mongoMappingConverter.getMappingContext().getPersistentEntity(entityClass).getCollection();
  }

  public EntityWithEvents findEvents(String entityTypeName) {
    Query q = new Query(where("_events").not().size(0)).with(new Sort(Sort.Direction.ASC, "_events.id"));
    return mongoTemplate.findOne(q, EntityWithEvents.class, collectionName(getClass(entityTypeName)));
  }

  public void deleteEvent(String entityTypeName, String id, SerializedEvent serEvent) {
    Query query = new Query(where("_id").is(id).and("_events.0.eventId").is(serEvent.getEventId()));

    // Increment by zero to stop Spring MongoDB incrementing by one

    Update update = new Update().pop("_events", Update.Position.FIRST).inc("version", 0);
    Class<?> entityClass = getClass(entityTypeName);
    WriteResult wr = mongoTemplate.updateFirst(query, update, entityClass);
    if (wr.getN() != 1)
      throw new RuntimeException("update failed: " + wr.getN());
  }

  private Class<?> getClass(String entityTypeName) {
    Class<?> entityClass = null;
    try {
      entityClass = Class.forName(entityTypeName);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return entityClass;
  }

}
