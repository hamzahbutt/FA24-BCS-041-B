import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private boolean isCompleted;
    
    public Rental(String rentalId, Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCompleted = false;
        
        calculateTotalCost();
    }
    
    private void calculateTotalCost() {
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        days = Math.max(1, days);
        this.totalCost = vehicle.calculateRentalCost(days);
    }
    
    // Complete rental and return vehicle to available state
    public void completeRental() {
        this.isCompleted = true;
        vehicle.setAvailable(true);
    }

    public String getRentalId() {
        return rentalId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public double getTotalCost() {
        return totalCost;
    }
    
    public boolean isCompleted() {
        return isCompleted;
    }
    
    @Override
    public String toString() {
        return "Rental ID: " + rentalId + " | Customer: " + customer.getName() + 
               " | Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() +
               " | From: " + startDate + " | To: " + endDate +
               " | Cost: $" + String.format("%.2f", totalCost);
    }
}