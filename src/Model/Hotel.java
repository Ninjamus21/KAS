package Model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String address;
    private double pris;
    private boolean isDoubleværelse;
    private ArrayList<HotelTilkøb> hotelTilkøbs = new ArrayList<>();
    private ArrayList<Konference> konferences = new ArrayList<>();
    private ArrayList<Tilmelding> tilmeldings = new ArrayList<>();

    public Hotel(String navn, String address, double pris, boolean isDoubleværelse) {
        this.navn = navn;
        this.address = address;
        this.pris = pris;
        this.isDoubleværelse = isDoubleværelse;
    }

    public ArrayList<HotelTilkøb> getHotelTilkøbs() {
        return new ArrayList<>(hotelTilkøbs);
    }

    public HotelTilkøb createHotelTilkøb(String tillægNavn, int tillægPris) {
        HotelTilkøb hotelTilkøb = new HotelTilkøb(tillægNavn, tillægPris);
        hotelTilkøbs.add(hotelTilkøb);
        return hotelTilkøb;
    }

    public void addKonference(Konference konference) {
        if (!konferences.contains(konference)) {
            konferences.add(konference);
            konference.addHotel(this);
        }
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldings.contains(tilmelding)) {
            tilmeldings.add(tilmelding);
            tilmelding.addHotel(this);
        }
    }

    public double getPris() {
        if (isDoubleværelse) {
            return pris * 1.25;
        }
        return pris;
    }
}
