import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MenuService {
    private Scanner scanner;
    private AuthenticationService authService;
    private RentalSystem rentalSystem;

    public MenuService(AuthenticationService authService, RentalSystem rentalSystem) {
        this.scanner = new Scanner(System.in);
        this.authService = authService;
        this.rentalSystem = rentalSystem;
    }

    public void displayWelcomeMenu() {
        while (true) {
            System.out.println("\n========== CAR RENTAL SYSTEM ==========");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    handleSignUp();
                    break;
                case 3:
                    System.out.println("Thank you for using Car Rental System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (authService.isLoggedIn()) {
                displayMainMenu();
            }
        }
    }

    private void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (authService.login(username, password)) {
            System.out.println("Login successful! Welcome, " + authService.getCurrentUser().getUsername());
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void handleSignUp() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (authService.signUp(username, password, email)) {
            System.out.println("Account created successfully! You can now login.");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private void displayMainMenu() {
        while (authService.isLoggedIn()) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Inventory");
            System.out.println("2. My Rentals");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    displayInventory();
                    break;
                case 2:
                    displayMyRentals();
                    break;
                case 3:
                    authService.logout();
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayInventory() {
        List<Vehicle> availableCars = rentalSystem.getAvailableVehicles();

        while (true) {
            System.out.println("\n===== AVAILABLE CARS =====");
            if (availableCars.isEmpty()) {
                System.out.println("No cars available for rent.");
                return;
            }

            for (int i = 0; i < availableCars.size(); i++) {
                System.out.println((i + 1) + ". " + availableCars.get(i));
            }

            System.out.println("\n0. Back to Main Menu");
            System.out.print("Select a car to view details or 0 to go back: ");
            int choice = getIntInput();

            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= availableCars.size()) {
                displayCarDetails(availableCars.get(choice - 1));
            } else {
                System.out.println("Invalid selection.");
            }
        }
    }

    private void displayCarDetails(Vehicle vehicle) {
        System.out.println("\n===== CAR DETAILS =====");
        System.out.println("Registration: " + vehicle.getRegistrationNumber());
        System.out.println("Brand: " + vehicle.getBrand());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Year: " + vehicle.getYear());
        System.out.println("Daily Rate: $" + vehicle.getBaseRatePerDay());

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            System.out.println("Type: " + car.getCarType());
            System.out.println("Seats: " + car.getSeatingCapacity());
            System.out.println("AC: " + (car.hasAC() ? "Yes" : "No"));
        } else if (vehicle instanceof SUV) {
            SUV suv = (SUV) vehicle;
            System.out.println("Type: SUV");
            System.out.println("Seats: " + suv.getSeatingCapacity());
            System.out.println("4WD: " + (suv.is4WheelDrive() ? "Yes" : "No"));
        }

        System.out.println("\n1. Rent this car");
        System.out.println("0. Back to inventory");
        System.out.print("Choose an option: ");

        int choice = getIntInput();

        if (choice == 1) {
            rentVehicle(vehicle);
        }
    }

    private void rentVehicle(Vehicle vehicle) {
        Customer customer = ensureCustomerProfile();
        if (customer == null) return;

        System.out.print("Enter number of rental days: ");
        int rentalDays = getIntInput();
        if (rentalDays < 1) {
            System.out.println("Must rent for at least 1 day.");
            return;
        }

        String rentalId = generateRentalId();
        Rental rental = rentalSystem.createRental(rentalId, customer.getCustomerId(),
                vehicle.getRegistrationNumber(), rentalDays);

        if (rental != null) {
            System.out.println("\n===== RENTAL CONFIRMATION =====");
            System.out.println(rental);
            System.out.println("Rental created successfully!");
        } else {
            System.out.println("Failed to create rental. Please try again.");
        }
    }

    private Customer ensureCustomerProfile() {
        User currentUser = authService.getCurrentUser();
        Customer existingCustomer = rentalSystem.findCustomer(currentUser.getUserId());
        if (existingCustomer != null) {
            return existingCustomer;
        }

        System.out.println("\n===== COMPLETE YOUR PROFILE =====");
        System.out.print("Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Driving License Number: ");
        String license = scanner.nextLine();

        Customer newCustomer = new Customer(currentUser.getUserId(), name, phone,
                currentUser.getEmail(), license);
        rentalSystem.addCustomer(newCustomer);

        return newCustomer;
    }

    private void handleCarReturn() {
        System.out.println("\n===== RETURN A CAR =====");
        System.out.print("Enter Rental ID: ");
        String rentalId = scanner.nextLine();

        if (rentalSystem.completeRental(rentalId)) {
            System.out.println("Car returned successfully!");
        } else {
            System.out.println("Rental ID not found or already completed.");
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private void displayMyRentals() {
        Customer customer = rentalSystem.findCustomer(authService.getCurrentUser().getUserId());
        if (customer == null) {
            System.out.println("Customer profile not found.");
            return;
        }

        List<Rental> activeRentals = rentalSystem.getActiveRentalsForCustomer(customer.getCustomerId());

        System.out.println("\n===== MY RENTALS =====");
        if (activeRentals.isEmpty()) {
            System.out.println("You have no active rentals.");
            return;
        }

        for (int i = 0; i < activeRentals.size(); i++) {
            System.out.println((i + 1) + ". " + activeRentals.get(i));
        }

        System.out.println("\nEnter rental number to return (0 to go back): ");
        int choice = getIntInput();

        if (choice > 0 && choice <= activeRentals.size()) {
            Rental rental = activeRentals.get(choice - 1);
            if (rentalSystem.completeRental(rental.getRentalId())) {
                System.out.println("Car returned successfully!");
            } else {
                System.out.println("Failed to return car.");
            }
        }
    }

    private String generateRentalId() {
        return "R" + System.currentTimeMillis();
    }
}