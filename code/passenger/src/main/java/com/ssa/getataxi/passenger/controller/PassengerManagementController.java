package com.ssa.getataxi.passenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.getataxi.passenger.domain.Passenger;
import com.ssa.getataxi.passenger.services.PassengerManagementService;

@RestController
public class PassengerManagementController {

	@Autowired
	private PassengerManagementService passengerManagementService;
	
	@RequestMapping(value="/passengers/{id}", method=RequestMethod.GET)
	public PassengerResponse getPassenger(@PathVariable String id){
		
		Passenger passenger = passengerManagementService.getPassengerById(id);
		System.out.println(passenger.isAccountEnabled());
		return new PassengerResponse(passenger.getId(), passenger.isAccountEnabled());
	}
}
