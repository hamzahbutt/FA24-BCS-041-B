public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private String email;
    private String drivingLicense;
    
    public Customer(String customerId, String name, String phoneNumber, String email, String drivingLicense) {
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.drivingLicense = drivingLicense;
    }
    
   
    public String getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getDrivingLicense() {
        return drivingLicense;
    }
    
    @Override
    public String toString() {
        return name + " (ID: " + customerId + ")";
    }
}