public class ParkingSystem {
    private String campus;
    private Supervisor supervisor;
    private ParkingZone[] zones;
    private PermitHolder[] permitHolders;
    private int zoneCount;
    private int permitHolderCount;
    
    
    private static ParkingSystem instance = null;
    
   
    private ParkingSystem(String campus, Supervisor supervisor) {
        this.campus = campus;
        this.supervisor = supervisor;
        this.zones = new ParkingZone[10]; 
        this.permitHolders = new PermitHolder[10]; 
        this.zoneCount = 0;
        this.permitHolderCount = 0;
    }
   
    public static ParkingSystem getInstance(String campus, Supervisor supervisor) {
        if (instance == null) {
            if (supervisor == null) {
                System.out.println("Error: System cannot function without a supervisor.");
                return null;
            }
            instance = new ParkingSystem(campus, supervisor);
            return instance;
        } else {
            System.out.println("Warning: ParkingSystem instance already exists. Returning existing instance.");
            return instance;
        }
    }
    
    public void addZone(ParkingZone zone) {
        if (zoneCount < zones.length) {
            zones[zoneCount++] = zone;
        }
    }
    
    public void addPermitHolder(PermitHolder permitHolder) {
        if (permitHolderCount < permitHolders.length) {
            permitHolders[permitHolderCount++] = permitHolder;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Campus: ").append(campus).append("\n");
        sb.append("Supervisor: ").append(supervisor.toString()).append("\n");
        
        sb.append("Zones: [");
        for (int i = 0; i < zoneCount; i++) {
            sb.append(zones[i].toString());
            if (i < zoneCount - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n");
        
        sb.append("Permit Holders: [");
        for (int i = 0; i < permitHolderCount; i++) {
            sb.append(permitHolders[i].toString());
            if (i < permitHolderCount - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }
}
