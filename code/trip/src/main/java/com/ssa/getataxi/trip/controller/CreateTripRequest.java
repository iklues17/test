package com.ssa.getataxi.trip.controller;

import net.chrisrichardson.getataxi.domain.Location;

public class CreateTripRequest {

	private String PassengerId;
	
	private Location pickupLocation;

	public String getPassengerId() {
		return PassengerId;
	}

	public void setPassengerId(String passengerId) {
		PassengerId = passengerId;
	}

	public Location getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(Location pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	
	

}
