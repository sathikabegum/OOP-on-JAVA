import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bike {
    private String bikeId;
    private String brand;
    private String model;
    private double basePricePerHour;
    private boolean isAvailable;

    public Bike(String bikeId, String brand, String model, double basePricePerHour) {
        this.bikeId = bikeId;
        this.brand = brand;
        this.model = model;
        this.basePricePerHour = basePricePerHour;
        this.isAvailable = true;
    }

    public String getBikeId() {
        return bikeId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalHours) {
        return basePricePerHour * rentalHours;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnBike() {
        isAvailable = true;
    }
}

class BikeRentalSystem {
    private List<Bike> bikes;

    public BikeRentalSystem() {
        bikes = new ArrayList<>();
    }

    public void addBike(Bike bike) {
        bikes.add(bike);
    }

    public void rentBike(Bike bike, int rentalHours) {
        if (bike.isAvailable()) {
            bike.rent();
            System.out.println("Bike rented successfully.");
            System.out.printf("Total Price: $%.2f%n", bike.calculatePrice(rentalHours));
        } else {
            System.out.println("Bike is not available for rent.");
        }
    }

    public void returnBike(Bike bike) {
        bike.returnBike();
        System.out.println("Bike returned successfully.");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Bike Rental System =====");
            System.out.println("1. Rent a Bike");
            System.out.println("2. Return a Bike");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\n== Rent a Bike ==\n");
                System.out.println("Available Bikes:");
                for (Bike bike : bikes) {
                    if (bike.isAvailable()) {
                        System.out.println(bike.getBikeId() + " - " + bike.getBrand() + " " + bike.getModel());
                    }
                }

                System.out.print("\nEnter the bike ID you want to rent: ");
                String bikeId = scanner.nextLine();

                System.out.print("Enter the number of hours for rental: ");
                int rentalHours = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Bike selectedBike = null;
                for (Bike bike : bikes) {
                    if (bike.getBikeId().equals(bikeId) && bike.isAvailable()) {
                        selectedBike = bike;
                        break;
                    }
                }

                if (selectedBike != null) {
                    rentBike(selectedBike, rentalHours);
                } else {
                    System.out.println("\nInvalid bike selection or bike not available for rent.");
                }
            } else if (choice == 2) {
                System.out.println("\n== Return a Bike ==\n");
                System.out.print("Enter the bike ID you want to return: ");
                String bikeId = scanner.nextLine();

                Bike bikeToReturn = null;
                for (Bike bike : bikes) {
                    if (bike.getBikeId().equals(bikeId) && !bike.isAvailable()) {
                        bikeToReturn = bike;
                        break;
                    }
                }

                if (bikeToReturn != null) {
                    returnBike(bikeToReturn);
                } else {
                    System.out.println("Invalid bike ID or bike is not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Bike Rental System!");
    }
}

public class Main {
    public static void main(String[] args) {
        BikeRentalSystem rentalSystem = new BikeRentalSystem();

        Bike bike1 = new Bike("B001", "Harley-Davidson", "Street 750", 20.0);
        Bike bike2 = new Bike("B002", "Ducati", "Monster 821", 25.0);
        Bike bike3 = new Bike("B003", "Honda", "CBR650R", 18.0);

        rentalSystem.addBike(bike1);
        rentalSystem.addBike(bike2);
        rentalSystem.addBike(bike3);

        rentalSystem.menu(); // Call the menu after adding bikes
    }
}
