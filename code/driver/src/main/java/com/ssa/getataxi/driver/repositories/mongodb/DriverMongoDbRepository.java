package com.ssa.getataxi.driver.repositories.mongodb;

import java.util.Optional;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.repositories.mongodb.AbstractMongoDbRepository;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import static org.springframework.data.mongodb.core.query.NearQuery.near;

import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;

import com.ssa.getataxi.driver.domain.Driver;
import com.ssa.getataxi.driver.repositories.DriverRepository;

@Repository
public class DriverMongoDbRepository extends AbstractMongoDbRepository<Driver> implements DriverRepository{
	
	public DriverMongoDbRepository(){
		super(Driver.class);
	}

	public DriverMongoDbRepository(Class<Driver> entityClass) {
		super(entityClass);
	}

	public Optional<Driver> findNearestAvailableDriver(Location location) {
				
		NearQuery near = NearQuery
				.near(new Point(location.getLon(), location.getLat()))
				.maxDistance(new Distance(100000, Metrics.KILOMETERS))
				.num(10);
		
		@SuppressWarnings("unused")
		GeoResults<Driver> result = mongoTemplate.geoNear(near , Driver.class);
		
//		result.getContent().stream().map(mapper)
		
//		Location nearLocation;
//		Point location = new Point(nearLocation.getLon(), nearLocation.getLat());
//		NearQuery query = near(location).maxDistance(new Distance(10, Metrics.MILES));
//		
////		GeoResults<DispatcherDriver> result2 = mongoTemplate.geoNear(near , DispatcherDriver.class);
//		GeoResults<Driver> result2 = mongoTemplate.geoNear(near , Driver.class);
//		
//		result.getContent().stream().map(GeoResult::getContent).filter(Driver::isAvailable).findFirst();
		
		return null;
	}
}
