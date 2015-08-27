package com.ssa.getataxi.driver;

import com.ssa.getataxi.driver.domain.Driver;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.util.misc.IdGenerator;

public class DriverMother {

	public static Driver makeDriver() {
		Driver driver = new Driver();
		driver.setId(IdGenerator.generateId());
		driver.setLocation(new Location(123456d, 654321d));
		return driver;
	}

}
