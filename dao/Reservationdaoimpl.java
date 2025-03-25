package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.ReservationStatus;
import entity.Reservationmodel;
import exception.ReservationException;
import util.ConnectionHelper;

public class Reservationdaoimpl implements Reservationdao{
	 @Override
	    public Reservationmodel getReservationById(int reservationId) throws ReservationException{
	        String query = "SELECT * FROM Reservation WHERE ReservationID = ?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, reservationId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                return new Reservationmodel(
	                    rs.getInt("ReservationID"),
	                    rs.getInt("CustomerID"),
	                    rs.getInt("VehicleID"),
	                    rs.getTimestamp("StartDate"),
	                    rs.getTimestamp("EndDate"),
	                    rs.getDouble("TotalCost")
	                );
	            } else {
	                throw new ReservationException("Reservation not found with ID: " + reservationId);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	    @Override
	    public List<Reservationmodel> getReservationsByCustomerId(int customerId) {
	        List<Reservationmodel> reservations = new ArrayList<>();
	        String query = "SELECT * FROM Reservation WHERE CustomerID = ?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, customerId);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                reservations.add(new Reservationmodel(
	                    rs.getInt("ReservationID"),
	                    rs.getInt("CustomerID"),
	                    rs.getInt("VehicleID"),
	                    rs.getTimestamp("StartDate"),
	                    rs.getTimestamp("EndDate"),
	                    rs.getDouble("TotalCost")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return reservations;
	    }
	 
	    @Override
	    public boolean createReservation(Reservationmodel reservation) {
	        String query = "INSERT INTO Reservation (CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status) VALUES (?, ?, ?, ?, ?, ?)";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, reservation.getCustomerID());
	            stmt.setInt(2, reservation.getVehicleID());
	            stmt.setTimestamp(3, new Timestamp(reservation.getStartDate().getTime()));
	            stmt.setTimestamp(4, new Timestamp(reservation.getEndDate().getTime()));
	            stmt.setDouble(5, reservation.getTotalCost());
	stmt.setString(6, ReservationStatus.pending.name());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    @Override
	    public boolean updateReservation(Reservationmodel reservation) {
	        String query = "UPDATE Reservation SET StartDate=?, EndDate=?, TotalCost=? WHERE ReservationID=?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setTimestamp(1, new Timestamp(reservation.getStartDate().getTime()));
	            stmt.setTimestamp(2, new Timestamp(reservation.getEndDate().getTime()));
	            stmt.setDouble(3, reservation.getTotalCost());
	            stmt.setInt(4, reservation.getReservationID());
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 
	    @Override
	    public boolean cancelReservation(int reservationId) {
	        String query = "UPDATE Reservation SET Status = ? WHERE ReservationID = ?";
	        try (Connection conn = ConnectionHelper.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	stmt.setString(1, ReservationStatus.cancelled.name());
	            stmt.setInt(2, reservationId);
	            return stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

}
