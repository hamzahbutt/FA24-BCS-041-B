public class Customer extends User {
    private String phoneNumber;
    private String drivingLicense;

    public Customer(String userId, String username, String password, String email) {
        super(userId, username, password, email, "CUSTOMER");
    }

    public Customer(String customerId, String name, String phoneNumber, String email, String drivingLicense) {
        super(customerId, name, "", email, "CUSTOMER");
        this.phoneNumber = phoneNumber;
        this.drivingLicense = drivingLicense;
    }

    public String getCustomerId() {
        return getUserId();
    }

    public String getName() {
        return getUsername();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    @Override
    public String toString() {
        return getName() + " (ID: " + getCustomerId() + ")";
    }
}