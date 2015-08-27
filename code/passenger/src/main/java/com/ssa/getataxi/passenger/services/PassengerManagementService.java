package com.ssa.getataxi.passenger.services;

import com.ssa.getataxi.passenger.domain.Passenger;

public interface PassengerManagementService {
	
	public Passenger registerPassenger(Passenger passengerInfo);

	public Passenger getPassengerById(String passengerId);
	
}
