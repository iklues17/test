package com.ssa.getataxi.trip.repositories;

import com.ssa.getataxi.trip.domain.Trip;

public interface PassengerRepository {

	Trip findById(String passengerId);
	
	Object findByPastTrips(String passengerId);

}
