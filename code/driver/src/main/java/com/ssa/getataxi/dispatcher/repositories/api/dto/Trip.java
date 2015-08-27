package com.ssa.getataxi.dispatcher.repositories.api.dto;

import net.chrisrichardson.getataxi.domain.Location;

public class Trip {
	private String id;
	private Location pickupLocation;
	private Location destLocation;
	private String passengerId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Location getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(Location pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public Location getDestLocation() {
		return destLocation;
	}
	public void setDestLocation(Location destLocation) {
		this.destLocation = destLocation;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	
	
}
