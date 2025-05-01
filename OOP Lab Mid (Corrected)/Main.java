public class Main {
    public static void main(String[] args) {
        Supervisor supervisor = new Supervisor("Dr. Ayesha", 5);
        ParkingSystem system = ParkingSystem.getInstance("CUI Lahore", supervisor);
        // If tried again, same instance will be returned
        ParkingSystem secondAttempt = ParkingSystem.getInstance("ShouldNotwork", supervisor);
        
        ParkingZone zone1 = new ParkingZone();
        ParkingZone zone2 = new ParkingZone();
        
        Owner owner1 = new Owner("Ali"); // ownerID will be 0001
        Owner owner2 = new Owner("Zara"); // ownerID will be 0002
        
        Vehicle v1 = new Vehicle("LEA123", "Car", owner1); // Allowed
        Vehicle v2 = new Vehicle("LEB456", "Bike", owner2); // Allowed
        Vehicle v3 = new Vehicle("LEA123", "Car", owner1); // Rejected, prints error
        
        zone1.addVehicle(v1);
        zone1.addVehicle(v2);
        zone2.addVehicle(v3);
        
        system.addZone(zone1);
        system.addZone(zone2);
        
        PermitHolder ph1 = new PermitHolder("Sara");
        PermitHolder ph2 = new PermitHolder("Hassan");
        
        system.addPermitHolder(ph1);
        system.addPermitHolder(ph2);
        
        System.out.println(system);
    }
}
