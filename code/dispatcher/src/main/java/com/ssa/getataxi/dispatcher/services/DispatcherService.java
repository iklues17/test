package com.ssa.getataxi.dispatcher.services;

import com.ssa.getataxi.commondomain.events.TripOfferedToDriver;
import com.ssa.getataxi.commondomain.events.TripCreatedEvent;

import net.chrisrichardson.getataxi.events.DomainEventHandler;
import net.chrisrichardson.getataxi.events.EventEnvelope;

public interface DispatcherService extends DomainEventHandler{

	public void handleTripCreatedEvent(EventEnvelope<TripCreatedEvent> eventEnvelope);

	public void handleOfferAcceptedEvent(EventEnvelope<TripOfferedToDriver> eventEnvelope);
}
