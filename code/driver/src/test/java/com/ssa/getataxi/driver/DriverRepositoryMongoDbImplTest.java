package com.ssa.getataxi.driver;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssa.getataxi.dispatcher.repositories.mongodb.DispatcherMongoDbTestConfiguration;
import com.ssa.getataxi.driver.domain.Driver;
import com.ssa.getataxi.driver.repositories.DriverRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =
 DispatcherMongoDbTestConfiguration.class)
@IntegrationTest
public class DriverRepositoryMongoDbImplTest {

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void testFindNearestAvailableDriver() throws Exception {
		mongoTemplate.remove(new Query(), Driver.class);
		
		Driver driver = DriverMother.makeDriver();
		driverRepository.add(driver);
		
		Optional<Driver> availableDriver = driverRepository.findNearestAvailableDriver(driver.getLocation());
		
//		assertEquals(availableDriver.get().getId(), driver.getId());
	}
}
