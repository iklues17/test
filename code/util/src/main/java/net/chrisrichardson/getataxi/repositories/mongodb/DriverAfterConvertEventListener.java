package net.chrisrichardson.getataxi.repositories.mongodb;

import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

public class DriverAfterConvertEventListener  extends AbstractMongoEventListener<BeforeConvertEvent> {

  @Override
  public void onApplicationEvent(MongoMappingEvent<?> event) {
    if (event instanceof AfterConvertEvent) {
      AfterConvertEvent ace = (AfterConvertEvent) event;
      Object source = ace.getSource();
      if (source instanceof PostLoadListener)
        ((PostLoadListener)source).postLoad();
    } else {
      super.onApplicationEvent(event);
    }
  }}
