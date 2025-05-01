public class Vehicle {
    private static java.util.HashSet<String> existingLicensePlates = new java.util.HashSet<>();
    
    String license;
    String type;
    Owner owner;

    public Vehicle(String license, String type, Owner owner) {
        
        if (existingLicensePlates.contains(license)) {
            System.out.println("Error: Duplicate license plate '" + license + "' is not allowed.");
            this.license = null;
            this.type = null;
            this.owner = new Owner(null);
            return;
        }
        
        existingLicensePlates.add(license);
        this.license = license;
        this.type = type;
        this.owner = owner;
    }
    
   
    public Vehicle shallowCopy() {
        Vehicle copy = new Vehicle(this.license, this.type, this.owner);
        return copy;
    }
    
    
    public Vehicle deepCopy() {
        Owner newOwner = new Owner(this.owner.name);
        Vehicle copy = new Vehicle(this.license, this.type, newOwner);
        return copy;
    }
    
    @Override
    public String toString() {
        if (license == null) {
            return "License Plate: null, Type: null, Owner: [null]";
        }
        return "License Plate: " + this.license + ", Type: " + this.type + ", Owner: [" + owner.toString() + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (!(obj instanceof Vehicle)) { return false; }
        
        Vehicle other = (Vehicle) obj;
        return this.license != null && this.license.equals(other.license);
    }
}
