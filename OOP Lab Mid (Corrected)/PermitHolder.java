public class PermitHolder extends Person {
    private static int baseID = 999;
    int permitID;
    
    public PermitHolder(String name) {
        super(name);
        this.permitID = ++baseID;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Permit ID: " + permitID;
    }
}
