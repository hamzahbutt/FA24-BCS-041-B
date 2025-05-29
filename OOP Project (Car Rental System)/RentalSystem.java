import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private List<Vehicle> vehicles;
    private List<Customer> customers;
    private List<Rental> rentals;

    public RentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(String registrationNumber) {
        vehicles.removeIf(v -> v.getRegistrationNumber().equals(registrationNumber));
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public Rental createRental(String rentalId, String customerId,
                               String registrationNumber, int rentalDays) {
        Customer customer = findCustomer(customerId);
        Vehicle vehicle = findVehicle(registrationNumber);

        if (customer == null || vehicle == null || !vehicle.isAvailable()) {
            return null;
        }

        vehicle.setAvailable(false);
        Rental rental = new Rental(rentalId, customer, vehicle, rentalDays);
        rentals.add(rental);
        return rental;
    }

    public boolean completeRental(String rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId().equals(rentalId) && !rental.isCompleted()) {
                rental.completeRental();
                return true;
            }
        }
        return false;
    }

    public List<Rental> getActiveRentals() {
        List<Rental> activeRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            if (!rental.isCompleted()) {
                activeRentals.add(rental);
            }
        }
        return activeRentals;
    }

    private Vehicle findVehicle(String registrationNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                return vehicle;
            }
        }
        return null;
    }

    public void printVehicleInventory() {
        System.out.println("\n===== VEHICLE INVENTORY =====");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle + " | Available: " + (vehicle.isAvailable() ? "Yes" : "No"));
            }
        }
    }

    public void printCustomerList() {
        System.out.println("\n===== CUSTOMER LIST =====");
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    public void printActiveRentals() {
        System.out.println("\n===== ACTIVE RENTALS =====");
        List<Rental> activeRentals = getActiveRentals();
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals.");
        } else {
            for (Rental rental : activeRentals) {
                System.out.println(rental);
            }
        }
    }
    public List<Rental> getActiveRentalsForCustomer(String customerId) {
        List<Rental> customerRentals = new ArrayList<>();
        for (Rental rental : rentals) {
            if (!rental.isCompleted() && rental.getCustomer().getCustomerId().equals(customerId)) {
                customerRentals.add(rental);
            }
        }
        return customerRentals;
    }
}