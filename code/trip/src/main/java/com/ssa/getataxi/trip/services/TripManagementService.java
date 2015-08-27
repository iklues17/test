package com.ssa.getataxi.trip.services;

import com.ssa.getataxi.trip.domain.Trip;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.events.DomainEventHandler;

public interface TripManagementService extends DomainEventHandler{
	
	public Trip createTrip(String passengerId, Location pickupLocation) ;
	
	public void cancelTrip(Trip tripID) ;
	
	public String viewPastTrips(String userID) ;
	
	public void acceptTrip(String tripID) ;
	
	public void rejectTrip(String tripID) ;
	
	public void pickUpPassenger(String tripID) ;
	
	public void dropOffPassanger(String tripID) ;
	
	public String getTripInfo(String TripID) ;
	
	public void addWayPoint(String TripID, Location locationinfo) ;
	
	public String viewTrip(String TripID) ;
	
}
