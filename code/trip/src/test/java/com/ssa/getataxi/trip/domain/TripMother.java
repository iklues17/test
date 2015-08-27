package com.ssa.getataxi.trip.domain;

import com.ssa.getataxi.trip.domain.Trip;

import net.chrisrichardson.getataxi.util.misc.IdGenerator;

public class TripMother {

	public static Trip makeTrip() {
		Trip trip = new Trip();
		trip.setId(IdGenerator.generateId());
		return trip;
	}

}
