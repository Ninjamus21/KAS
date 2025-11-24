package Model;

public class HotelTilkøb {
    private String tillægNavn;
    private int tillægPris;

    public HotelTilkøb(String tillægNavn, int tillægPris) {
        this.tillægNavn = tillægNavn;
        this.tillægPris = tillægPris;
    }

    public double getTillægPris() {
        return tillægPris;
    }
}
