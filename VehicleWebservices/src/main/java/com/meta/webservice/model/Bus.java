package com.meta.webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bus")
public class Bus extends Vehicle {

	public static final String TYPE_BUS		= "Bus";
	public static final String BUS_PASSENGERS	= "48";

	public Bus() {
		
	}

	public Bus(String chassisSeries, String chassisNumber) {
		super(chassisSeries, chassisNumber);
	}

	public Bus(String chassisSeries, String chassisNumber, String color) {
		super(chassisSeries, chassisNumber, color);
	}

	@Override
	public String getType() {
		return TYPE_BUS;
	}

	@Override
	public String getPassengers() {
		return BUS_PASSENGERS;
	}

}
