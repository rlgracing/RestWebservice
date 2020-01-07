package com.meta.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="car")
public class Car extends Vehicle {

	public static final String TYPE_CAR		= "Car";
	public static final String CAR_PASSENGERS	= "2";

	public Car() {
		
	}

	public Car(String chassisSeries, String chassisNumber) {
		super(chassisSeries, chassisNumber);
	}

	public Car(String chassisSeries, String chassisNumber, String color) {
		super(chassisSeries, chassisNumber, color);
	}

	@Override
	public String getType() {
		return TYPE_CAR;
	}

	@Override
	public String getPassengers() {
		return CAR_PASSENGERS;
	}

}
