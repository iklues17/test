package com.ssa.getataxi.commondomain.events;

import net.chrisrichardson.getataxi.events.Event;


public class DriverAcceptedTripEvent implements Event{
	private String tripId;

	public DriverAcceptedTripEvent(String tripId) {
		this.tripId = tripId;
	}
	
	public String getTripId() {
		return tripId;
	}
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	
}
