package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String name;
    private String address;
    private LocalDate startDato;
    private LocalDate slutDato;
    private KonferenceOrganisation konferenceOrganisation;
    private ArrayList<Udflugt> udflugts = new ArrayList<>();
    private ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Tilmelding> tilmeldings = new ArrayList<>();

    public Konference(String name, String address, LocalDate startDato, LocalDate slutDato, KonferenceOrganisation konferenceOrganisation) {
        this.name = name;
        this.address = address;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.konferenceOrganisation = konferenceOrganisation;
    }

    public Udflugt createUdflugt(String name, LocalDate date, double price, boolean frokostIncluded) {
        Udflugt udflugt = new Udflugt(name, date, price, frokostIncluded, this);
        udflugts.add(udflugt);
        return udflugt;
    }

    public void addHotel(Hotel hotel) {
        if (!hotels.contains(hotel)) {
            hotels.add(hotel);
            hotel.addKonference(this);
        }
    }

    public Tilmelding createTilmelding(Deltager deltager) {
        Tilmelding tilmelding = new Tilmelding(deltager, this);
        tilmeldings.add(tilmelding);
        return tilmelding;
    }

    public KonferenceOrganisation getKonferenceOrganisation() {
        return konferenceOrganisation;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public String toString() {
        return name + " at " + address + " from " + startDato + " to " + slutDato;
    }

    public ArrayList<Udflugt> getUdflugts() {
        return udflugts;
    }

    public String getName() {
        return name;
    }
}
