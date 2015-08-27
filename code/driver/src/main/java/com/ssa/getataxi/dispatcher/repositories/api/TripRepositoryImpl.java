package com.ssa.getataxi.dispatcher.repositories.api;

import org.springframework.stereotype.Component;

import com.ssa.getataxi.dispatcher.repositories.TripRepository;
import com.ssa.getataxi.dispatcher.repositories.api.dto.Trip;

@Component
public class TripRepositoryImpl implements TripRepository {

	public Trip findOne(String tripId) {
		return new Trip();
	}

}
