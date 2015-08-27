package com.ssa.getataxi.driver.domain;


public interface DriverManageService {
	
	public String registerDriver(Driver user);
	public String viewTripList(String dId);
	
	public void updateClockIn(String dId);
	public void updateClockOut(String dId);
	public void updateDriverAccept(String dId, String tripID);
	public void updateDriverReject(String dId, String tripID);
	public void updateDriverPickUp(String dId, String tripID, locInfo loc);
	public void updateDriverDropOff(String dId, String tripID, locInfo loc);
	public void updateDriverLocation(String dId, locInfo loc);
	
	public String getDriverList(String tripId, locInfo loc);
	
}
