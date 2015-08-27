package com.ssa.getataxi.dispatcher.services;

import com.ssa.getataxi.commondomain.events.TripOfferedToDriver;
import com.ssa.getataxi.commondomain.events.TripCreatedEvent;
import com.ssa.getataxi.commondomain.events.TripStarted;

import net.chrisrichardson.getataxi.events.DomainEventHandler;
import net.chrisrichardson.getataxi.events.EventEnvelope;

public interface DispatcherService extends DomainEventHandler{

	public void handleTripOfferedToDriver(EventEnvelope<TripOfferedToDriver> eventEnvelope);

	public void handleTripStarted(EventEnvelope<TripStarted> eventEnvelope);
}
