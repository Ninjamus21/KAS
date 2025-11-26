package Model;

public class HotelTilkøb {
    private String tillægNavn;
    private double tillægPris;

    public HotelTilkøb(String tillægNavn, double tillægPris) {
        this.tillægNavn = tillægNavn;
        this.tillægPris = tillægPris;
    }

    public double getTillægPris() {
        return tillægPris;
    }

    public String getTillægNavn() {
        return tillægNavn;
    }

    public String toString() {
        return tillægNavn + " - Pris: " + tillægPris;
    }
}
