package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class KonferenceOrganisation {
    private String name;
    private ArrayList<Konference> konferences;

    public KonferenceOrganisation(String name) {
        this.name = name;
    }

    public void createKonference(String name, String address, LocalDate startDato, LocalDate slutDato){
        Konference konference = new Konference(name, address, startDato, slutDato, this);
        konferences.add(konference);
    }

    public ArrayList<Konference> getKonferences(){
        return new ArrayList<>(konferences);
    }

    public double getPrisForDeltagerType(DeltagerType deltagerType) {
        return deltagerType.getPris();
    }
}
