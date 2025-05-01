public class Supervisor extends Person {
    int yearsOfexp;

    public Supervisor(String name, int yearsOfexp) {
        super(name);
        this.yearsOfexp = yearsOfexp;
    }

    @Override
    public String toString() {
        return super.toString() + ", Experience: " + yearsOfexp + " years";
    }
}
