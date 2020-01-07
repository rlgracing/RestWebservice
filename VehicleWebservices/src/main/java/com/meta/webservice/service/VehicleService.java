package com.meta.webservice.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.meta.webservice.dao.VehicleDAO;
import com.meta.webservice.model.Bus;
import com.meta.webservice.model.Car;
import com.meta.webservice.model.ResponseStatus;
import com.meta.webservice.model.Truck;
import com.meta.webservice.model.Vehicle;

@Path("/vehicle")
public class VehicleService {

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseStatus addVehicle(String vehicle) {
		System.out.println("Chegou ADD: " + vehicle);
		Vehicle v = null;
		try {

			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vehicleMap = mapper.readValue(vehicle, Map.class);
			String type = vehicleMap.get("type");
			String chassisSeries = vehicleMap.get("chassisSeries");
			String chassisNumber = vehicleMap.get("chassisNumber");
			String color = vehicleMap.get("color");
			
			if(type == null || "".equals(type)) return new ResponseStatus("5", "Type field is mandatory");
			if(chassisSeries == null || "".equals(chassisSeries)) return new ResponseStatus("6", "Chassis Series field is mandatory");
			if(chassisNumber == null || "".equals(chassisNumber)) return new ResponseStatus("7", "Chassis Number field is mandatory");
			if(color == null || "".equals(color)) return new ResponseStatus("8", "Color field is mandatory");
			
			ResponseStatus respFind = getVehicle(chassisSeries, chassisNumber);
			
			if(respFind.getVehicle() != null) return new ResponseStatus("4", "Vehicle already exists");
			
			switch(type) {
				case Bus.TYPE_BUS		: v = new Bus(chassisSeries, chassisNumber, color); break;
				case Truck.TYPE_TRUCK	: v = new Truck(chassisSeries, chassisNumber, color); break;
				case Car.TYPE_CAR		: v = new Car(chassisSeries, chassisNumber, color); break;
			}

			VehicleDAO.addVehicle(v);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseStatus("-1", "Exception on vehicle insert: " + e.getMessage());
		}
		
		return new ResponseStatus("", "Vehicle added", v);
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseStatus updateVehicle(String vehicle) {
		System.out.println("Chegou UPDATE: " + vehicle);
		Vehicle v = null;
		try {

			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> vehicleMap = mapper.readValue(vehicle, Map.class);
			String type = vehicleMap.get("type");
			String chassisSeries = vehicleMap.get("chassisSeries");
			String chassisNumber = vehicleMap.get("chassisNumber");
			String color = vehicleMap.get("color");

			if(type == null || "".equals(type)) return new ResponseStatus("5", "Type field is mandatory");
			if(chassisSeries == null || "".equals(chassisSeries)) return new ResponseStatus("6", "Chassis Series field is mandatory");
			if(chassisNumber == null || "".equals(chassisNumber)) return new ResponseStatus("7", "Chassis Number field is mandatory");
			if(color == null || "".equals(color)) return new ResponseStatus("8", "Color field is mandatory");

			ResponseStatus respFind = getVehicle(chassisSeries, chassisNumber);

			if(!respFind.getErrorCode().isEmpty()) return respFind;
			
			Vehicle prior = respFind.getVehicle();
			
			if(!prior.getType().equals(type))  return new ResponseStatus("3", "Changing vehicle type is not allowed");
				
			switch(type) {
				case Bus.TYPE_BUS		: v = new Bus(chassisSeries, chassisNumber, color); break;
				case Truck.TYPE_TRUCK	: v = new Truck(chassisSeries, chassisNumber, color); break;
				case Car.TYPE_CAR		: v = new Car(chassisSeries, chassisNumber, color); break;
			}

			VehicleDAO.updateVehicle(v);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseStatus("-1", "Exception on vehicle update: " + e.getMessage());
		}
		
		return new ResponseStatus("", "Vehicle updated", v);
	}
	
	@DELETE
	@Path("/{series}/{number}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseStatus deleteVehicle(@PathParam("series") String series, @PathParam("number") String number) {
		System.out.println("Chegou DELETE: " + series + " " + number);
		
		if(series == null || "".equals(series)) return new ResponseStatus("6", "Chassis Series field is mandatory");
		if(number == null || "".equals(number)) return new ResponseStatus("7", "Chassis Number field is mandatory");
		
		ResponseStatus responseFind = getVehicle(series, number);
		
		if(!responseFind.getErrorCode().isEmpty()) return responseFind;
		
		VehicleDAO.deleteVehicle(series, number);

		return new ResponseStatus("", "Vehicle deleted");
	}
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseStatus getAll() {
		List<Vehicle> list = VehicleDAO.getAllVehicles();
		
		if(list == null || list.isEmpty()) return new ResponseStatus("1", "No vehicles found");
		
		return new ResponseStatus("", "", list);
	}
	
	@GET
	@Path("/{series}/{number}")
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseStatus getVehicle(@PathParam("series") String series, @PathParam("number") String number) {
		System.out.println("Chegou FIND: " + series + " " + number);

		if(series == null || "".equals(series)) return new ResponseStatus("6", "Chassis Series field is mandatory");
		if(number == null || "".equals(number)) return new ResponseStatus("7", "Chassis Number field is mandatory");

		Vehicle vehicle = VehicleDAO.getVehicle(series, number);
		
		if(vehicle == null) return new ResponseStatus("2", "Vehicle not found");
		
		return new ResponseStatus("", "", vehicle);
	}

}
