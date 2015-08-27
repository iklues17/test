package com.ssa.getataxi.driver.domain;

import java.util.List;

import net.chrisrichardson.getataxi.domain.Location;
import net.chrisrichardson.getataxi.events.Event;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="driver")
public class Driver {
	@Id
	String id;
	String version;
	String name;
	String telNo;
	String carNo;
	Location location;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<Event> offerTrip(String entityId) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Event> assignTrip(String entityId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
