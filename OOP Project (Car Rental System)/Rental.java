public class Rental {
    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;
    private double totalCost;
    private boolean isCompleted;

    public Rental(String rentalId, Customer customer, Vehicle vehicle, int rentalDays) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.isCompleted = false;
        this.totalCost = vehicle.calculateRentalCost(rentalDays);
    }

    public void completeRental() {
        this.isCompleted = true;
        vehicle.setAvailable(true);
    }

    // Getters
    public String getRentalId() { return rentalId; }
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }
    public int getRentalDays() { return rentalDays; }
    public double getTotalCost() { return totalCost; }
    public boolean isCompleted() { return isCompleted; }

    @Override
    public String toString() {
        return "Rental ID: " + rentalId + " | Customer: " + customer.getName() +
                " | Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel() +
                " | Days: " + rentalDays + " | Cost: $" + String.format("%.2f", totalCost);
    }
}