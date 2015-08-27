package com.ssa.getataxi.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.services.TripManagementService;

@RestController
public class TripManagementController {

	@Autowired
	private TripManagementService tripManagementService;
	
	@RequestMapping(value="/trips", method=RequestMethod.POST)
	public CreateTripResponse createTrip(@RequestBody CreateTripRequest createTripRequest){
		
		Trip newTrip = tripManagementService.createTrip(createTripRequest.getPassengerId(), createTripRequest.getPickupLocation());
		
		return new CreateTripResponse(newTrip.getId(), newTrip.getPassengerId());
	}
}
