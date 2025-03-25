package dao;

import java.util.List;

import entity.Vehiclemodel;
import exception.VehicleNotFoundException;

public interface VehicleDao {
	Vehiclemodel getVehicleById(Vehiclemodel veh) throws VehicleNotFoundException;
	List<Vehiclemodel> getAvailableVehicles();
	boolean addVehicle(Vehiclemodel vehicle);
	boolean updatevehicle(Vehiclemodel vehicle);
	boolean removeVehicle(int vehicleId);
	
	
	

}
