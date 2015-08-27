package net.chrisrichardson.getataxi.events.repositories.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "someTestEntity")
public class SomeTestEntity {

    @Id
    private String id;

    @Version
    private long version;

}
