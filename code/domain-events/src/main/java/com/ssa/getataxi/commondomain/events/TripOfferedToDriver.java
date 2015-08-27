package com.ssa.getataxi.commondomain.events;

import net.chrisrichardson.getataxi.events.Event;


public class TripOfferedToDriver implements Event{
	
	// needed information for this event : driverid, tripid, status, 
	
	private String driverId;
	
	private String tripId;
	
	private String status;
	

	public TripOfferedToDriver(String driverId, String tripId, String status) {
		this.driverId = driverId;
		this.tripId = tripId;
		this.status = status;
	}


	public String getDriverId() {
		return driverId;
	}


	public String getTripId() {
		return tripId;
	}


	public String getStatus() {
		return status;
	}
	
}
