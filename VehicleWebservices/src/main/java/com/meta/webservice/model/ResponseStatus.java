package com.meta.webservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="responseStatus")
public class ResponseStatus {

	private String errorCode = "";
	private String message = "";
	private Vehicle vehicle = null;
	private List<Vehicle> listVehicles = null;
	
	public ResponseStatus() {
		
	}
	
	public ResponseStatus(String errorCode, String message, Vehicle vehicle) {
		this.errorCode = errorCode;
		this.message = message;
		this.vehicle = vehicle;
	}

	public ResponseStatus(String errorCode, String message, List<Vehicle> listVehicle) {
		this.errorCode = errorCode;
		this.message = message;
		this.listVehicles = listVehicle;
	}
	
	public ResponseStatus(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Vehicle> getListVehicles() {
		return listVehicles;
	}

	public void setListVehicles(List<Vehicle> listVehicles) {
		this.listVehicles = listVehicles;
	}
}
