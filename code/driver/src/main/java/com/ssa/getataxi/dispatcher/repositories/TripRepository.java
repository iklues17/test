package com.ssa.getataxi.dispatcher.repositories;

import com.ssa.getataxi.dispatcher.repositories.api.dto.Trip;

public interface TripRepository {

	Trip findOne(String tripId);
}
