package com.ssa.getataxi.passenger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.getataxi.passenger.domain.Passenger;
import com.ssa.getataxi.passenger.repositories.PassengerRepository;

@Service
public class PassengerManagementServiceImpl implements PassengerManagementService{
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger registerPassenger(Passenger passengerInfo) {
		Passenger passengerInfoRegistered = null;
		
		// do register passengerInfo to the PERSISTENCE.
		
		
		return passengerInfoRegistered;
	}

	public Passenger getPassengerById(String passengerId) {
		System.out.println(passengerId);
		return passengerRepository.findOne(passengerId);
	}
}
