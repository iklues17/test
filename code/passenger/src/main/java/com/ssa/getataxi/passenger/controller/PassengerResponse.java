package com.ssa.getataxi.passenger.controller;

public class PassengerResponse {

	private String passengerId;
	
	private boolean accountEnabled;

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public boolean isAccountEnabled() {
		return accountEnabled;
	}

	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public PassengerResponse(String passengerId, boolean accountEnabled) {
		this.passengerId = passengerId;
		this.accountEnabled = accountEnabled;
	}
	
	public PassengerResponse(){
	}

}
