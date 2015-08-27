package net.suki.getataxi.driver.domain;

import org.springframework.stereotype.Repository;

import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;

@Repository
public class PassengerRepositoryImpl extends AbstractMongoDbRepository<Passenger> implements PassengerRepository{

	public PassengerRepositoryImpl() {
		super(Passenger.class);
	}
	
}
