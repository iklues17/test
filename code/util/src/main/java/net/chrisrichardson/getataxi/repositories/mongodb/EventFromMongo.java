package net.chrisrichardson.getataxi.repositories.mongodb;

import org.springframework.data.annotation.Id;

public class EventFromMongo {

  @Id
  String id;

  String _class;
}
