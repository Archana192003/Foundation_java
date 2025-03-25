package entity;

import java.util.Date;

public class Customermodel {
	private int CustomerID;
	private String FirstName;
	private String LastName;
	private String Email;
	private String PhoneNumber;
	private String Address;
	private String Username;
	private String PasswordHash;
	private Date Registrationdate;
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPasswordHash() {
		return PasswordHash;
	}
	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}
	public Date getRegistrationdate() {
		return Registrationdate;
	}
	public void setRegistrationdate(Date registrationdate) {
		Registrationdate = registrationdate;
	}
	public Customermodel(int customerID, String firstName, String lastName, String email, String phoneNumber,
			String address, String username, String passwordHash, Date registrationdate) {
		super();
		CustomerID = customerID;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		PhoneNumber = phoneNumber;
		Address = address;
		Username = username;
		PasswordHash = passwordHash;
		Registrationdate = registrationdate;
	}
	
	

}
