package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import entity.Vehiclemodel;
import exception.VehicleNotFoundException;
import util.ConnectionHelper;


		public class Vehicledaoimpl implements VehicleDao {
	 
	    @Override
	    public Vehiclemodel getVehicleById(Vehiclemodel veh) 
	    {
	        String query = "SELECT * FROM Vehicle WHERE VehicleID = ?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, veh.getVehicleID());
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new Vehiclemodel(
	                    rs.getInt("VehicleID"),
	                    rs.getString("Model"),
	                    rs.getString("Make"),
	                    Year.of(rs.getInt("Year")),
	                    rs.getString("Color"),
	                    rs.getString("RegistrationNumber"),
	                    rs.getDouble("Availability"),
	                    rs.getDouble("DailyRate")
	                );
	            } else {
	                throw new VehicleNotFoundException("Vehicle not found with ID: " + veh.getVehicleID());
	            }
	        } catch (SQLException | VehicleNotFoundException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	    @Override
	    public List<Vehiclemodel> getAvailableVehicles() {
	        List<Vehiclemodel> vehicles = new ArrayList<>();
	        String query = "SELECT * FROM Vehicle WHERE Availability = 1";
	        try (Connection conn = ConnectionHelper.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                vehicles.add(new Vehiclemodel(
	                    rs.getInt("VehicleID"),
	                    rs.getString("Model"),
	                    rs.getString("Make"),
	                    Year.of(rs.getInt("Year")),
	                    rs.getString("Color"),
	                    rs.getString("RegistrationNumber"),
	                    rs.getDouble("Availability"),
	                    rs.getDouble("DailyRate")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return vehicles;
	    }
	 
	    @Override
	    public boolean addVehicle(Vehiclemodel vehicle) {
	        String query = "INSERT INTO Vehicle (Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, vehicle.getModel());
	            stmt.setString(2, vehicle.getMake());
	            stmt.setInt(3, vehicle.getYear().getValue());
	            stmt.setString(4, vehicle.getColor());
	            stmt.setString(5, vehicle.getRegistrationNumber());
	            stmt.setDouble(6, vehicle.getAvailabblity());
	            stmt.setDouble(7, vehicle.getDailyRate());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    @Override
	    public boolean updatevehicle(Vehiclemodel vehicle) {
	        String query = "UPDATE Vehicle SET Model=?, Make=?, Year=?, Color=?, RegistrationNumber=?, Availability=?, DailyRate=? WHERE VehicleID=?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, vehicle.getModel());
	            stmt.setString(2, vehicle.getMake());
	            stmt.setInt(3, vehicle.getYear().getValue());
	            stmt.setString(4, vehicle.getColor());
	            stmt.setString(5, vehicle.getRegistrationNumber());
	            stmt.setDouble(6, vehicle.getAvailabblity());
	            stmt.setDouble(7, vehicle.getDailyRate());
	            stmt.setInt(8, vehicle.getVehicleID());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    @Override
	    public boolean removeVehicle(int vehicleId) {
	        String query = "DELETE FROM Vehicle WHERE VehicleID = ?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, vehicleId);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}
	
	 