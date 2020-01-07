package com.meta.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="truck")
public class Truck extends Vehicle {

	public static final String TYPE_TRUCK	= "Truck";
	public static final String TRUCK_PASSENGERS	= "1";

	public Truck() {
		
	}

	public Truck(String chassisSeries, String chassisNumber) {
		super(chassisSeries, chassisNumber);
	}

	public Truck(String chassisSeries, String chassisNumber, String color) {
		super(chassisSeries, chassisNumber, color);
	}
	
	@Override
	public String getType() {
		return TYPE_TRUCK;
	}

	@Override
	public String getPassengers() {
		return TRUCK_PASSENGERS;
	}

}
