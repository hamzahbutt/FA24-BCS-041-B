public abstract class Vehicle {
    private String registrationNumber;
    private String brand;
    private String model;
    private String imagePath;
    private int year;
    private boolean isAvailable;
    private double baseRatePerDay;
    
    public Vehicle(String registrationNumber, String brand, String model, int year, double baseRatePerDay) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = true;
    }
    
    public abstract double calculateRentalCost(int days);
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getYear() {
        return year;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public double getBaseRatePerDay() {
        return baseRatePerDay;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ") - " + registrationNumber;
    }
}