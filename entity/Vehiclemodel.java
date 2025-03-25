package entity;

import java.time.Year;

public class Vehiclemodel {
	private int VehicleID;
	private String Model;
	private String Make;
	private Year year;
	private String Color;
	private String RegistrationNumber;
	private double Availablity;
	private double DailyRate;
	public int getVehicleID() {
		return VehicleID;
	}
	public void setVehicleID(int vehicleID) {
		VehicleID = vehicleID;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getMake() {
		return Make;
	}
	public void setMake(String make) {
		Make = make;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getRegistrationNumber() {
		return RegistrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		RegistrationNumber = registrationNumber;
	}
	public Double getAvailabblity() {
		return Availablity;
	}
	public void setAvailabblity(Double availablity) {
		Availablity = availablity;
	}
	public double getDailyRate() {
		return DailyRate;
	}
	public void setDailyRate(double dailyRate) {
		DailyRate = dailyRate;
	}
	public Vehiclemodel(int vehicleID, String model, String make, Year year, String color, String registrationNumber,
			double availablity, double dailyRate) {
		super();
		VehicleID = vehicleID;
		Model = model;
		Make = make;
		this.year = year;
		Color = color;
		RegistrationNumber = registrationNumber;
		Availablity = availablity;
		DailyRate = dailyRate;
	}
	@Override
	public String toString() {
		return "Vehiclemodel [VehicleID=" + VehicleID + ", Model=" + Model + ", Make=" + Make + ", year=" + year
				+ ", Color=" + Color + ", RegistrationNumber=" + RegistrationNumber + ", Availablity=" + Availablity
				+ ", DailyRate=" + DailyRate + "]";
	}
	
	
	}
	
	


