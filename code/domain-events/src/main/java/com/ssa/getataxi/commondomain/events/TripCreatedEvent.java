package com.ssa.getataxi.commondomain.events;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.events.Event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TripCreatedEvent implements Event{
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	private String passengerId;
	private Location pickupLocation;
	
	public TripCreatedEvent() {
	}
	
	public TripCreatedEvent(String passengerId, Location pickLocation){
		this.passengerId = passengerId;
		this.pickupLocation = pickLocation;
	}

	public String getPassengerId(){
		return passengerId;
	}
	
	public Location getPiLocation() {
		return pickupLocation;
	}
}
