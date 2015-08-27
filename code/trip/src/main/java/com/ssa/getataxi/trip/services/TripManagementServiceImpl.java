package com.ssa.getataxi.trip.services;

import java.util.ArrayList;
import java.util.List;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.events.EventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.getataxi.commondomain.events.TripCreatedEvent;
import com.ssa.getataxi.trip.domain.Passenger;
import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.repositories.PassengerRepository;
import com.ssa.getataxi.trip.repositories.TripRepository;

@Service
public class TripManagementServiceImpl implements TripManagementService {

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private EventPublisher eventPublisher;
	
	public TripManagementServiceImpl(){}
	
	public TripManagementServiceImpl(TripRepository tripRepository,
			PassengerRepository passengerRepository) {
		this.passengerRepository = passengerRepository;
		this.tripRepository = tripRepository;
	}

	@Override
	public Trip createTrip(String passengerId, Location pickupLocation) {
		
		//1. verify passenger
		Passenger passenger = passengerRepository.findById(passengerId);
		if(!passenger.isAccountEnabled()){
//			throw new Exception("Account is disabled");
			System.out.println("Account is disabled");
		}
		// TODO
//		tripRepository.findActiveTrips();

		//2. create new trip
		Trip newTrip = new Trip();
		newTrip.setPassengerId(passengerId);
		newTrip.setPickupLocation(pickupLocation);
		
		List<Event> events = new ArrayList<Event>();
		events.add(new TripCreatedEvent(passengerId, pickupLocation));
		
		tripRepository.add(newTrip, events);
		
		return newTrip ;
	}

	@Override
	public void cancelTrip(Trip tripID) {
		// TODO Auto-generated method stub

	}

	@Override
	public String viewPastTrips(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void acceptTrip(String tripID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectTrip(String tripID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pickUpPassenger(String tripID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropOffPassanger(String tripID) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTripInfo(String TripID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWayPoint(String TripID, Location locationinfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public String viewTrip(String TripID) {
		// TODO Auto-generated method stub
		return null;
	}

}
