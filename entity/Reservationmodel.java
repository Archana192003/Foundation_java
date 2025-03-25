package entity;

import java.util.Date;

public class Reservationmodel {
	private int ReservationID;
	private int CustomerID;
	private int VehicleID;
	private Date StartDate;
	private Date EndDate;
	private Double TotalCost;
	public int getReservationID() {
		return ReservationID;
	}
	public void setReservationID(int reservationID) {
		ReservationID = reservationID;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getVehicleID() {
		return VehicleID;
	}
	public void setVehicleID(int vehicleID) {
		VehicleID = vehicleID;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public Double getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(Double totalCost) {
		TotalCost = totalCost;
	}
	public Reservationmodel(int reservationID, int customerID, int vehicleID, Date startDate, Date endDate,
			Double totalCost) {
		super();
		ReservationID = reservationID;
		CustomerID = customerID;
		VehicleID = vehicleID;
		StartDate = startDate;
		EndDate = endDate;
		TotalCost = totalCost;
	}
		

}
