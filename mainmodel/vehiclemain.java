package mainmodel;
 
import dao.VehicleDao;
import dao.Vehicledaoimpl;
import entity.Vehiclemodel;
import exception.VehicleNotFoundException;
 
import java.time.Year;
import java.util.List;
import java.util.Scanner;
 
public class vehiclemain {
    public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
        VehicleDao vehicleDao = new Vehicledaoimpl();
        
        while (true) {
            System.out.println("\n=== VEHICLE RENTAL MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicle by ID");
            System.out.println("3. View Available Vehicles");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Remove Vehicle");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    // Add Vehicle
                    System.out.print("Enter Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Enter Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Color: ");
                    String color = scanner.nextLine();
                    System.out.print("Enter Registration Number: ");
                    String regNumber = scanner.nextLine();
                    System.out.print("Enter Availability (1 for Available, 0 for Not Available): ");
                    double availability = scanner.nextDouble();
                    System.out.print("Enter Daily Rate: ");
                    double dailyRate = scanner.nextDouble();
                    
                    Vehiclemodel newVehicle = new Vehiclemodel(0, model, make, Year.of(year), color, regNumber, availability, dailyRate);
                    boolean added = vehicleDao.addVehicle(newVehicle);
                    System.out.println(added ? "Vehicle added successfully!" : "Failed to add vehicle.");
                    break;
                
                case 2:
                    // View Vehicle by ID
                    System.out.print("Enter Vehicle ID: ");
                    int vehicleId = scanner.nextInt();
                    
                    try {
                        Vehiclemodel vehicle = vehicleDao.getVehicleById(new Vehiclemodel(vehicleId, null, null, null, null, null, 0, 0));
                        System.out.println("Vehicle Found: " + vehicle);
                    } catch (VehicleNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 3:
                    // View Available Vehicles
                    List<Vehiclemodel> availableVehicles = vehicleDao.getAvailableVehicles();
                    if (availableVehicles.isEmpty()) {
                        System.out.println("No available vehicles.");
                    } else {
                        System.out.println("Available Vehicles:");
                        for (Vehiclemodel v : availableVehicles) {
                            System.out.println(v);
                        }
                    }
                    break;
                    
                case 4:
                    // Update Vehicle
                    System.out.print("Enter Vehicle ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Model: ");
                    String newModel = scanner.nextLine();
                    System.out.print("Enter New Make: ");
                    String newMake = scanner.nextLine();
                    System.out.print("Enter New Year: ");
                    int newYear = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Color: ");
                    String newColor = scanner.nextLine();
                    System.out.print("Enter New Registration Number: ");
                    String newRegNumber = scanner.nextLine();
                    System.out.print("Enter New Availability (1 for Available, 0 for Not Available): ");
                    double newAvailability = scanner.nextDouble();
                    System.out.print("Enter New Daily Rate: ");
                    double newDailyRate = scanner.nextDouble();
                    
                    Vehiclemodel updatedVehicle = new Vehiclemodel(updateId, newModel, newMake, Year.of(newYear), newColor, newRegNumber, newAvailability, newDailyRate);
                    boolean updated = vehicleDao.updatevehicle(updatedVehicle);
                    System.out.println(updated ? "Vehicle updated successfully!" : "Failed to update vehicle.");
                    break;
                    
                case 5:
                    // Remove Vehicle
                    System.out.print("Enter Vehicle ID to remove: ");
                    int removeId = scanner.nextInt();
                    
                    boolean removed = vehicleDao.removeVehicle(removeId);
                    System.out.println(removed ? "Vehicle removed successfully!" : "Failed to remove vehicle.");
                    break;
                    
                case 6:
                    // Exit
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}