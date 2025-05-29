public class Car extends Vehicle {
    private String carType;
    private int seatingCapacity;
    private boolean hasAC;
    
    public Car(String registrationNumber, String brand, String model, int year, 
              double baseRatePerDay, String carType, int seatingCapacity, boolean hasAC) {
        super(registrationNumber, brand, model, year, baseRatePerDay);
        this.carType = carType;
        this.seatingCapacity = seatingCapacity;
        this.hasAC = hasAC;
    }
    
    @Override
    public double calculateRentalCost(int days) {
        double cost = getBaseRatePerDay() * days;
        if (hasAC) {
            cost += (cost * 0.05);
        }
        return cost;
    }
    
    public String getCarType() {
        return carType;
    }
    
    public int getSeatingCapacity() {
        return seatingCapacity;
    }
    
    public boolean hasAC() {
        return hasAC;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | " + carType + " | Seats: " + seatingCapacity + 
               (hasAC ? " | AC: Yes" : " | AC: No");
    }
}