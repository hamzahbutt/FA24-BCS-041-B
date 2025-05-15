public class SUV extends Vehicle {
    private boolean is4WheelDrive;
    private int seatingCapacity;
    
    public SUV(String registrationNumber, String brand, String model, int year, 
              double baseRatePerDay, boolean is4WheelDrive, int seatingCapacity) {
        super(registrationNumber, brand, model, year, baseRatePerDay);
        this.is4WheelDrive = is4WheelDrive;
        this.seatingCapacity = seatingCapacity;
    }
    
    @Override
    public double calculateRentalCost(int days) {
        double cost = getBaseRatePerDay() * days;
        
        // Add premium for 4-wheel drive
        if (is4WheelDrive) {
            cost += (cost * 0.1);
        }
        
        return cost;
    }
    
    public boolean is4WheelDrive() {
        return is4WheelDrive;
    }
    
    public int getSeatingCapacity() {
        return seatingCapacity;
    }
    
    @Override
    public String toString() {
        return super.toString() + " | SUV | Seats: " + seatingCapacity + 
               (is4WheelDrive ? " | 4WD: Yes" : " | 4WD: No");
    }
}