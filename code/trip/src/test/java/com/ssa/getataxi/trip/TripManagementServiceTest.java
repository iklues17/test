package com.ssa.getataxi.trip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.testutil.repositories.MockRepositoryTestUtil;
import net.chrisrichardson.getataxi.util.misc.IdGenerator;

import org.junit.Before;
import org.junit.Test;

import com.ssa.getataxi.commondomain.events.TripCreatedEvent;
import com.ssa.getataxi.trip.domain.Passenger;
import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.repositories.PassengerRepository;
import com.ssa.getataxi.trip.repositories.TripRepository;
import com.ssa.getataxi.trip.services.TripManagementService;
import com.ssa.getataxi.trip.services.TripManagementServiceImpl;

public class TripManagementServiceTest {
	
	private TripManagementService tripManagementService;
	private TripRepository tripRepository;
	private PassengerRepository passengerRepository;
	
	@Before
	public void setUp() throws Exception {
		
		tripRepository = mock(TripRepository.class);
		passengerRepository = mock(PassengerRepository.class);
		tripManagementService = new TripManagementServiceImpl(tripRepository, passengerRepository);
	}
	
	@Test
	public void shouldCreateTrip(){
		String passengerId = IdGenerator.generateId();
		Location pickupLocation = new Location(123456d, 654321d);
		
		Passenger passenger = new Passenger();
		passenger.setAccountEnabled(true);
		
		when(passengerRepository.findById(passengerId)).thenReturn(passenger);
		
		MockRepositoryTestUtil.stubRespositoryAdd(tripRepository, Trip.class);
		
		Trip newTrip = tripManagementService.createTrip(passengerId, pickupLocation);
		
		assertNotNull(newTrip);
		
		TripCreatedEvent tripCreated = new TripCreatedEvent(passengerId, pickupLocation);
		
		verify(tripRepository).add(newTrip, Collections.singletonList(tripCreated));
		verify(passengerRepository).findById(passengerId);
		
		assertEquals(passengerId, newTrip.getPassengerId());
		assertEquals(pickupLocation, newTrip.getPickupLocation());
		
	}

}
