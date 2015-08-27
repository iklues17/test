package com.ssa.getataxi.driver.repositories;

import java.util.Optional;

import com.ssa.getataxi.driver.domain.Driver;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.repositories.Repository;

public interface DriverRepository extends Repository<Driver> {

	Optional<Driver> findNearestAvailableDriver(Location location);

}
