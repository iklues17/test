package com.ssa.getataxi.trip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssa.getataxi.trip.config.TripMongoDbTestConfiguration;
import com.ssa.getataxi.trip.domain.Trip;
import com.ssa.getataxi.trip.domain.TripMother;
import com.ssa.getataxi.trip.repositories.TripRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=TripMongoDbTestConfiguration.class)
@IntegrationTest
@ActiveProfiles("mockedClientProxies")
public class Act3TripManagementServiceIntegrationTest {
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void requestTrip(){
		mongoTemplate.remove(new Query(), Trip.class);
		
		Trip trip = TripMother.makeTrip();
		
		tripRepository.add(trip);
		
	}

}
