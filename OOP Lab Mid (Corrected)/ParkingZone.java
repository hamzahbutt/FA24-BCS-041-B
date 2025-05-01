public class ParkingZone {
    private static int counter = 0;
    String zoneID;
    Vehicle[] vehicles = new Vehicle[5];

    public ParkingZone() {
        counter++;
        this.zoneID = "Z" + counter;
        
        
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i] = null;
        }
    }

    public void addVehicle(Vehicle v) {
        if (v.license == null) {
            return; 
        }
        
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) {
                vehicles[i] = v;
                return;
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Zone ID: ").append(zoneID).append(", Vehicles: [");
        
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] == null) {
                sb.append("License Plate: null, Type: null, Owner: [null]");
            } else {
                sb.append(vehicles[i].toString());
            }
            
            if (i < vehicles.length - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        return sb.toString();
    }
}
