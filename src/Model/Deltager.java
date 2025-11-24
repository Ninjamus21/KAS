package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deltager {
    private String name;
    private int age;
    private String company;
    private String address;
    private String city;
    private String country;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private boolean isSpeaker;
    private String mobileNumber;
    private String companyNumber;
    private DeltagerType deltagerType;
    private Ledsager ledsager;
    private ArrayList<Tilmelding> tilmeldings = new ArrayList<>();

    // Constructor without Ledsager
    public Deltager(String name, int age, String address, String city, String country, boolean isSpeaker, String mobileNumber, DeltagerType deltagerType) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.city = city;
        this.country = country;
        this.isSpeaker = isSpeaker;
        this.mobileNumber = mobileNumber;
        this.deltagerType = deltagerType;
    }

    // Constructor with Ledsager
    public Deltager(String name, int age, String address, String city, String country, boolean isSpeaker, String mobileNumber, DeltagerType deltagerType, Ledsager ledsager) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.city = city;
        this.country = country;
        this.isSpeaker = isSpeaker;
        this.mobileNumber = mobileNumber;
        this.deltagerType = deltagerType;
        this.ledsager = ledsager;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public Ledsager createLedsager(String name) {
        Ledsager ledsager = new Ledsager(name, this);
        this.ledsager = ledsager;
        return ledsager;
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }

    public DeltagerType getDeltagerType() {
        return deltagerType;
    }
}
