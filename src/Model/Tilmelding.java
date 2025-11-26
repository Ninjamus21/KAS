package Model;

import java.util.ArrayList;

public class Tilmelding {
    private Deltager deltager;
    private Konference konference;
    private Hotel hotel;
    private ArrayList<HotelTilkøb> hotelTilkøbs = new ArrayList<>();
    private ArrayList<Udflugt> udflugts = new ArrayList<>();
    private ArrayList<Udflugt> udflugtTilmeldinger = new ArrayList<>();

    public Tilmelding(Deltager deltager, Konference konference) {
        this.deltager = deltager;
        this.konference = konference;
    }


    public Deltager getDeltager() {
        return deltager;
    }
    public void setDeltager(Deltager deltager) {
        this.deltager = deltager;
    }

    public Konference getKonference() {
        return konference;
    }
    public Hotel getHotel() {
        return hotel;
    }

    public void tilmeldDeltagerKonference(Deltager deltager, Konference konference) {
        if (!this.deltager.equals(deltager))
            this.deltager = deltager;
        if (!this.konference.equals(konference))
            this.konference = konference;
    }

    public void tilmeldLedsagerTilUdflugt(Deltager deltager, Udflugt udflugt) {
        if (deltager == null) return;
        Ledsager ledsager = deltager.getLedsager();
        if (ledsager == null) return;

        if (!udflugtTilmeldinger.contains(udflugt)) {
            udflugtTilmeldinger.add(udflugt);
            udflugt.addTilmelding(this);
        }
    }

    public boolean isLedsagerTilmeldtUdflugt(Deltager deltager, Udflugt udflugt) {
        Ledsager ledsager = deltager.getLedsager();
        if (ledsager == null) return false;
        System.out.println("Checking if ledsager " + ledsager.getName() + " is signed up for excursion " + udflugt);
        return udflugtTilmeldinger.contains(udflugt);
    }

    public void addHotel(Hotel hotel) {
        if (hotel == null) return;
        // only change if different hotel (guard against null this.hotel)
        if (this.hotel == null || !this.hotel.equals(hotel)) {
            this.hotel = hotel;
            // delegate to Hotel to keep consistency
            hotel.addTilmelding(this);
        }
    }

    public void addHotelTilkøb(HotelTilkøb hotelTilkøb) {
        if (!hotelTilkøbs.contains(hotelTilkøb)) {
            hotelTilkøbs.add(hotelTilkøb);
        }
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugts.contains(udflugt)) {
            udflugts.add(udflugt);
            udflugt.addTilmelding(this);
        }
    }

    public double beregnPris() {
        double totalPris = 0.0;

        // Pris for konference baseret på deltagerType
        totalPris += konference.getKonferenceOrganisation().getPrisForDeltagerType(deltager.getDeltagerType());

        // Pris for hotel
        if (hotel != null) {
            totalPris += hotel.getPris();
        }

        // Pris for hotel tilkøb
        for (HotelTilkøb tilkøb : hotelTilkøbs) {
            totalPris += tilkøb.getTillægPris();
        }

        // Pris for udflugter
        for (Udflugt udflugt : udflugts) {
            totalPris += udflugt.getPrice();
        }

        return totalPris;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}

