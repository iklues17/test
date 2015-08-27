package com.ssa.getataxi.trip.config;

import java.util.Collections;
import java.util.Set;

import net.chrisrichardson.getataxi.testutil.misc.StubServerUrlConfigurer;

public class TripManagementStubsConfigurer extends StubServerUrlConfigurer{

	@Override
	protected Set<String> getPropertyNames() {
		return Collections.singleton("passenger_service.url");
	}

}
