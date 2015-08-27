package com.ssa.getataxi.trip.repositories.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ssa.getataxi.trip.domain.Passenger;
import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.repositories.PassengerRepository;

@Component
public class PassengerApiRepository implements PassengerRepository {

	@Value("${passenger_service.url}")
	private String passengerServiceUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
//	@HystrixCommand
	public Passenger findById(String passengerId){
		ResponseEntity<Passenger> response = null;
		response = restTemplate.getForEntity(passengerServiceUrl+"/"+passengerId, Passenger.class);
		//TODO HTTP Status Ã³¸®
		
		return response.getBody();
		
	}
	
	@Override
	public List<Trip> findByPastTrips(String passengerId) {
		// TODO Auto-generated method stub
		return new ArrayList<Trip>();
	}

}
