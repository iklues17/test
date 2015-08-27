package com.ssa.getataxi.commondomain.events;

import net.chrisrichardson.getataxi.events.Event;


public class TripStarted implements Event{
	private String tripId;
	private String driverId;

	
	public TripStarted() {		
	}
	
	public TripStarted(String tripId){
		this.setTripId(tripId);
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}


}
