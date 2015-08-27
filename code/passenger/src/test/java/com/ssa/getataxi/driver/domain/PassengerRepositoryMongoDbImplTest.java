package com.ssa.getataxi.driver.domain;

import static org.junit.Assert.assertEquals;
import net.chrisrichardson.getataxi.util.misc.IdGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssa.getataxi.passenger.domain.Passenger;
import com.ssa.getataxi.passenger.repositories.PassengerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PassengerMongoDbTestConfiguration.class)
@IntegrationTest
public class PassengerRepositoryMongoDbImplTest {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Test
	public void shouldRegisterPassnger() throws Exception {

		String passengerId = IdGenerator.generateId();
		String name = "TestUser";
		String password = "user123!";
		String verifiedPassword = "user123!";
		String email = "testuser@google.com";
		
		Passenger newPassenger = new Passenger();
		newPassenger.setPassengerId(passengerId);
		newPassenger.setEmail(email);
		newPassenger.setName(name);
		newPassenger.setPassword(password);
		
		Passenger registeredPassenger = passengerRepository.add(newPassenger);
		
//		Passenger retrievedPassenger = passengerRepository.findOne(passengerId);
		
		System.out.println("Id: " + registeredPassenger.getId());
		
		assertEquals(newPassenger.getName(), registeredPassenger.getName());
		
		mongoTemplate.remove(registeredPassenger);
	}
	
}
