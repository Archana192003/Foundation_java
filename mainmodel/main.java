package mainmodel;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import dao.Admindaoimpl;
import dao.Customerdaoimpl;
import dao.Reservationdaoimpl;
import dao.Vehicledaoimpl;
import entity.Adminmodel;
import entity.Customermodel;
import entity.Reservationmodel;
import entity.Vehiclemodel;
import exception.AdminNotFoundException;

public class main {
	 private static final Customerdaoimpl customerDao = new Customerdaoimpl();
	    private static final Vehicledaoimpl vehicleDao = new Vehicledaoimpl();
	    private static final Reservationdaoimpl reservationDao = new Reservationdaoimpl();
	    private static final Admindaoimpl adminDao = new Admindaoimpl();
	 
	    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	        
	        while (true) {
	            System.out.println("\n=== CarConnect Car Rental System ===");
	            System.out.println("1. Register Customer");
	            System.out.println("2. Login as Customer");
	            System.out.println("3. View Available Vehicles");
	            System.out.println("4. Make a Reservation");
	            System.out.println("5. Cancel a Reservation");
	            System.out.println("6. Admin Login");
	            System.out.println("7. Exit");
	            System.out.print("Choose an option: ");
	            
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	 
	            switch (choice) {
	                case 1:
	                    registerCustomer(scanner);
	                    break;
	                case 2:
	                    loginCustomer(scanner);
	                    break;
	                case 3:
	                    viewAvailableVehicles();
	                    break;
	                case 4:
	                    makeReservation(scanner);
	                    break;
	                case 5:
	                    cancelReservation(scanner);
	                    break;
	                case 6:
	                    loginAdmin(scanner);
	                    break;
	                case 7:
	                    System.out.println("Exiting... Thank you for using CarConnect!");
	                    scanner.close();
	                    return;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	 
	    private static void registerCustomer(Scanner scanner) {
	        System.out.print("Enter First Name: ");
	        String firstName = scanner.nextLine();
	        System.out.print("Enter Last Name: ");
	        String lastName = scanner.nextLine();
	        System.out.print("Enter Email: ");
	        String email = scanner.nextLine();
	        System.out.print("Enter Phone Number: ");
	        String phone = scanner.nextLine();
	        System.out.print("Enter Address: ");
	        String address = scanner.nextLine();
	        System.out.print("Enter Username: ");
	        String username = scanner.nextLine();
	        System.out.print("Enter Password: ");
	        String passwordhash = scanner.nextLine();
	 
	        Customermodel customer = new Customermodel(0, firstName, lastName, email, phone, address, username,passwordhash, new java.util.Date());
	        
	        if (customerDao.registerCustomer(customer)) {
	            System.out.println("Customer registered successfully!");
	        } else {
	            System.out.println("Registration failed. Try again.");
	        }
	    }
	 
	    private static void loginCustomer(Scanner scanner) {
	        System.out.print("Enter Username: ");
	        String username = scanner.nextLine();
	        
	        Customermodel customer = customerDao.getCustomerByUsername(username);
	        if (customer != null) {
	            System.out.println("Login successful. Welcome, " + customer.getFirstName() + "!");
	        } else {
	            System.out.println("Invalid username or password.");
	        }
	    }
	 
	    private static void viewAvailableVehicles() {
	        List<Vehiclemodel> vehicles = vehicleDao.getAvailableVehicles();
	        if (vehicles.isEmpty()) {
	            System.out.println("No available vehicles at the moment.");
	        } else {
	            System.out.println("\nAvailable Vehicles:");
	            for (Vehiclemodel vehicle : vehicles) {
	                System.out.println("ID: " + vehicle.getVehicleID() + " | " + vehicle.getMake() + " " + vehicle.getModel() + " | Daily Rate: $" + vehicle.getDailyRate());
	            }
	        }
	    }
	 
	    private static void makeReservation(Scanner scanner) {
	        System.out.print("Enter Customer ID: ");
	        int customerId = scanner.nextInt();
	        System.out.print("Enter Vehicle ID: ");
	        int vehicleId = scanner.nextInt();
	        System.out.print("Enter Rental Days: ");
	        int days = scanner.nextInt();
	 
	        Vehiclemodel vehicle = vehicleDao.getVehicleById(new Vehiclemodel(vehicleId, "", "", null, "", "", 1, 0.0));
	        if (vehicle == null) {
	            System.out.println("Invalid vehicle ID.");
	            return;
	        }
	 
	        double totalCost = days * vehicle.getDailyRate();
	        Timestamp startDate = new Timestamp(System.currentTimeMillis());
	        Timestamp endDate = new Timestamp(startDate.getTime() + (days * 24L * 60 * 60 * 1000));
	 
	        Reservationmodel reservation = new Reservationmodel(0, customerId, vehicleId, startDate, endDate, totalCost);
	        if (reservationDao.createReservation(reservation)) {
	            System.out.println("Reservation successful! Total cost: $" + totalCost);
	        } else {
	            System.out.println("Reservation failed.");
	        }
	    }
	 
	    private static void cancelReservation(Scanner scanner) {
	        System.out.print("Enter Reservation ID: ");
	        int reservationId = scanner.nextInt();
	        
	        if (reservationDao.cancelReservation(reservationId)) {
	            System.out.println("Reservation canceled successfully.");
	        } else {
	            System.out.println("Cancellation failed.");
	        }
	    }
	 
	    private static void loginAdmin(Scanner scanner) {
	        System.out.print("Enter Admin Username: ");
	        String username = scanner.nextLine();
	 
	        Adminmodel admin;
	        try {
	            admin = adminDao.getAdminByUsername(new Adminmodel(0, "", "", "", "", username, "", null));
	            if (admin != null) {
	                System.out.println("Admin login successful. Welcome, " + admin.getFirstName() + "!");
	            }
	        } catch (AdminNotFoundException e) {
	            System.out.println("Admin not found.");
	        }
	    }
	}



