public class Owner extends Person {
    private static int numOfOwners = 0;
    String ownerID;

    public Owner(String name) {
        super(name);
        numOfOwners++;
        this.ownerID = String.format("%04d", numOfOwners);
    }

    @Override
    public String toString() {
        return super.toString() + ", Owner ID: " + ownerID;
    }
}
