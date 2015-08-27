package com.ssa.getataxi.trip.repositories;

import com.ssa.getataxi.trip.domain.Passenger;
import com.ssa.getataxi.trip.domain.Trip;

public interface PassengerRepository {

	Passenger findById(String passengerId);
	
	Object findByPastTrips(String passengerId);

}
