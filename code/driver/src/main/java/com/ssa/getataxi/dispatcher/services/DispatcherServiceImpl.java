package com.ssa.getataxi.dispatcher.services;

import java.util.List;
import java.util.Optional;

import net.chrisrichardson.getataxi.events.Event;
import net.chrisrichardson.getataxi.events.EventEnvelope;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssa.getataxi.commondomain.events.TripCreatedEvent;
import com.ssa.getataxi.commondomain.events.TripOfferedToDriver;
import com.ssa.getataxi.commondomain.events.TripStarted;
import com.ssa.getataxi.dispatcher.repositories.TripRepository;
import com.ssa.getataxi.driver.domain.Driver;
import com.ssa.getataxi.driver.repositories.DriverRepository;

@Service
public class DispatcherServiceImpl implements DispatcherService {

	@Autowired
	private TripRepository tripRepository;
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public DispatcherServiceImpl(){}

	public void handleTripCreatedEvent(EventEnvelope<TripCreatedEvent> eventEnvelope) {
		System.out.println("handleTripCreatedEvent");
		System.out.println(eventEnvelope.getEvent().getPassengerId());

		TripCreatedEvent tripCreated = eventEnvelope.getEvent();
//		Event offeredToDriver = new TripOfferedToDriver(IdGenerator.generateId(), tripCreated.getTripId(), "CREATED");

		Optional<Driver> availableDriver = driverRepository.findNearestAvailableDriver(tripCreated.getPiLocation());
		System.out.println("availableDriver:" + availableDriver);
		//Push to Driver

	}
	
	public void handleTripOfferedToDriver(EventEnvelope<TripOfferedToDriver> eventEnvelope){
		Driver driver = driverRepository.findOne(eventEnvelope.getEvent().getDriverId());
		List<Event> events = driver.offerTrip(eventEnvelope.getEntityId());
		driverRepository.update(driver, events);
	}
	
	public void handleTripStarted(EventEnvelope<TripStarted> eventEnvelope){
		Driver driver = driverRepository.findOne(eventEnvelope.getEvent().getDriverId());
		List<Event> events = driver.assignTrip(eventEnvelope.getEntityId());
		driverRepository.update(driver, events);
		
	}

}
