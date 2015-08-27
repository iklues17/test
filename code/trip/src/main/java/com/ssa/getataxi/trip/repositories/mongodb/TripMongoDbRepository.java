package com.ssa.getataxi.trip.repositories.mongodb;

import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;

import org.springframework.stereotype.Repository;

import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.repositories.TripRepository;

@Repository
public class TripMongoDbRepository extends AbstractMongoDbRepository<Trip> implements TripRepository{

	public TripMongoDbRepository(){
		super(Trip.class);
	}
	
	public TripMongoDbRepository(Class<Trip> trip) {
		super(trip);
	}

}
