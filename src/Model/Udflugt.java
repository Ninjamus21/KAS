package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String name;
    private LocalDate date;
    private double price;
    private boolean frokostIncluded;
    private Konference konference;
    private ArrayList<Tilmelding> tilmeldings = new ArrayList<>();

    public Udflugt(String name ,LocalDate date, double price, boolean frokostIncluded, Konference konference) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.frokostIncluded = frokostIncluded;
        this.konference = konference;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldings.contains(tilmelding)) {
            tilmeldings.add(tilmelding);
            tilmelding.addUdflugt(this);
        }
    }

    public double getPrice() {
        return price;
    }

    public Konference getKonference() {
        return konference;
    }
    @Override
    public String toString() {
        return name + " ( " + date + " ) " + "- Pris: " + price + (frokostIncluded ? " (Frokost inkluderet)" : "Ingen frokost");
    }
}
