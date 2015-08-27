package com.ssa.getataxi.trip.controller;

public class CreateTripResponse {

	private String tripId;
	
	private String passengerId;

	public CreateTripResponse(){
	}
	
	public CreateTripResponse(String tripId, String passengerId) {
		this.tripId = tripId;
		this.passengerId = passengerId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	

	
}
