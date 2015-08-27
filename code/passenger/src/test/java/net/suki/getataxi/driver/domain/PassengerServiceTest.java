package net.suki.getataxi.driver.domain;

import static org.mockito.Mockito.mock;
import net.chrisrichardson.getataxi.testutil.repositories.MockRepositoryTestUtil;
import net.chrisrichardson.getataxi.util.misc.IdGenerator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PassengerServiceTest {
	private PassengerService passengerService;
	
	private PassengerRepository passengerRepository;
	
	
	@Before
	public void setUp() throws Exception {
		passengerRepository = mock(PassengerRepository.class);
		
		passengerService = new PassengerServiceImpl();
		
	}
	
	@Test
	public void shouldRegisterPassenger() {
		String passengerId = IdGenerator.generateId();
		String name = "TestUser";
		String password = "user123!";
		String verifiedPassword = "user123!";
		String email = "testuser@google.com";
		
		Passenger passengerInfo = new Passenger();
		passengerInfo.setPassengerId(passengerId);
		passengerInfo.setEmail(email);
		passengerInfo.setName(name);
		passengerInfo.setPassword(password);
		
		MockRepositoryTestUtil.stubRespositoryAdd(passengerRepository, Passenger.class);
		
		Passenger passengerInfoRegistered = passengerService.registerPassenger(passengerInfo);
		
		assertNotNull(passengerInfoRegistered);
		
		assertEquals(name, passengerInfoRegistered.getName());
		
		
	}
	
//	@Test
//	public void shouldRegisterPassenger() {
//		String passengerId = IdGenerator.generateId();
//		String name = "TestUser";
//		String password = "user123!";
//		String verifiedPassword = "user123!";
//		String email = "testuser@google.com";
//		
//		PassengerInfo passengerInfo = new PassengerInfo();
//		passengerInfo.setPassengerId(passengerId);
//		passengerInfo.setEmail(email);
//		passengerInfo.setName(name);
//		passengerInfo.setPassword(password);
//		
//		MockRepositoryTestUtil.stubRespositoryAdd(passengerRepository, PassengerInfo.class);
//		
//		boolean isPassengerRegistered = passengerService.registerPassenger(passengerInfo);
//		
//		assertTrue(isPassengerRegistered);
//	}
	
}
