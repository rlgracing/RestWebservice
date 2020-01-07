package com.meta.webservice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.meta.webservice.model.Bus;
import com.meta.webservice.model.Car;
import com.meta.webservice.model.Truck;
import com.meta.webservice.model.Vehicle;

public class VehicleDAO {

	//Mock DB
	private static Map<String, Vehicle> vehicleMap = new HashMap<String, Vehicle>();
	static {
		vehicleMap.put("X1", new Bus("X", "1", "Blue"));
		vehicleMap.put("Y2", new Car("Y", "2", "Red"));
		vehicleMap.put("Z3", new Truck("Z", "3", "Green"));
	}
	
	public static void addVehicle(Vehicle vehicle) {
		vehicleMap.put(vehicle.getChassisSeries()+vehicle.getChassisNumber(), vehicle);
	}
	
	public static void deleteVehicle(String chassisSeries, String chassisNumber) {
		vehicleMap.remove(chassisSeries+chassisNumber);
	}
	
	public static void updateVehicle(Vehicle vehicle) {
		vehicleMap.put(vehicle.getChassisSeries()+vehicle.getChassisNumber(), vehicle);
	}
	
	public static List<Vehicle> getAllVehicles() {
		return new ArrayList<Vehicle>(vehicleMap.values());
	}
	
	public static Vehicle getVehicle(String chassisSeries, String chassisNumber) {
		return vehicleMap.get(chassisSeries+chassisNumber);
	}

}
