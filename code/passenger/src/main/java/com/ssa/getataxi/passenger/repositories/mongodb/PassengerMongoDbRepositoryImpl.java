package com.ssa.getataxi.passenger.repositories.mongodb;

import org.springframework.stereotype.Repository;

import com.ssa.getataxi.passenger.domain.Passenger;
import com.ssa.getataxi.passenger.repositories.PassengerRepository;

import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;

@Repository
public class PassengerMongoDbRepositoryImpl extends AbstractMongoDbRepository<Passenger> implements PassengerRepository{

	public PassengerMongoDbRepositoryImpl() {
		super(Passenger.class);
	}
	
}
