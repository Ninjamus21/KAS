package Model;

public class Ledsager {
    private final String name;
    private Deltager deltager;

    public Ledsager(String name, Deltager deltager) {
        this.name = name;
        this.deltager = deltager;
    }
    public Ledsager(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
}
