package com.ssa.getataxi.trip.domain;

import java.util.ArrayList;

import net.chrisrichardson.getataxi.domain.Location;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="trip")
public class Trip {
	@Id
	private String id;
	private String version;
	private Location pickupLocation;
	private Location destLocation;
	private String passengerId;
	private String driverId;
	private String status;
	private String billId;
	private ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
	private long createTime;
	private long lastUpdateTime;
	
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
	public ArrayList<Waypoint> getWaypoints() {
		return waypoints;
	}
	public void setWaypoints(ArrayList<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
	public Location getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(Location pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public Location getDestLocation() {
		return destLocation;
	}
	public void setDestLocation(Location destLocation) {
		this.destLocation = destLocation;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public ArrayList<Waypoint> getRoute() {
		return waypoints;
	}
	public void setWaypoint(double latitue, double longitude, String name) {
		Waypoint way = new Waypoint();
		way.setLatitude(latitue);
		way.setLongitude(longitude);
		way.setTimestamp(System.currentTimeMillis());
		waypoints.add(way);
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	/*public String convertJsonRount(ArrayList<Location> waypoints) {
		String jsonValue = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			jsonValue = ow.writeValueAsString(waypoints);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonValue;
	}*/
}
