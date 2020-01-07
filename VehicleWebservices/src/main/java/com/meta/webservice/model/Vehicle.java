package com.meta.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="vehicle")
public abstract class Vehicle {
		
	private String chassisSeries;
	private String chassisNumber;
	private String color;
	private String type;
	private String passengers;

	public Vehicle() {
		
	}

	public Vehicle(String chassisSeries, String chassisNumber) {
		this.chassisSeries = chassisSeries;
		this.chassisNumber = chassisNumber;
	}

	public Vehicle(String chassisSeries, String chassisNumber, String color) {
		this.chassisSeries = chassisSeries;
		this.chassisNumber = chassisNumber;
		this.color = color;
	}
	
	public String getChassisSeries() {
		return chassisSeries;
	}
	public void setChassisSeries(String chassisSeries) {
		this.chassisSeries = chassisSeries;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chasisNumber) {
		this.chassisNumber = chasisNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public abstract String getType();
	
	public void setType(String type) {
		this.type = type;
	}

	public abstract String getPassengers();

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}


}
