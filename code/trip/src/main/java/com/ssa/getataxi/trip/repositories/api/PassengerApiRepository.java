package com.ssa.getataxi.trip.repositories.api;

import java.util.ArrayList;
import java.util.List;

import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.repositories.PassengerRepository;

public class PassengerApiRepository implements PassengerRepository {

	@Override
	public List<Trip> findByPastTrips(String passengerId) {
		// TODO Auto-generated method stub
		return new ArrayList<Trip>();
	}

	@Override
	public Trip findById(String passengerId) {
		// TODO Auto-generated method stub
		return new Trip();
	}
}
