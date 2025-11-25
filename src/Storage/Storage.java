package Storage;

import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Hotel> hotels = new ArrayList<>();
    private static final ArrayList<Konference> konferences = new ArrayList<>();
    private static final ArrayList<Udflugt> udflugts = new ArrayList<>();

    public static void initStorage() {
        // Hotels
        Hotel hotel1 = new Hotel("Hotel Scandinavia", "HC Andersen boulevarden 44", 250, false);
        Hotel hotel1D = new Hotel("Hotel Scandinavia - D", "HC Andersen boulevarden 44", 250, true);
        Hotel hotel2 = new Hotel("Hotel Odense", "Jernbanegade 22", 200, false);
        Hotel hotel2D = new Hotel("Hotel Odense - D", "Jernbanegade 22", 200, true);
        Hotel hotel3 = new Hotel("Hotel Faaborg", "Nørregade 12", 150, true);
        hotels.add(hotel1);
        hotels.add(hotel1D);
        hotels.add(hotel2);
        hotels.add(hotel2D);
        hotels.add(hotel3);

        // Sample Konference + organisation
        KonferenceOrganisation org = new KonferenceOrganisation("Default Org");
        Konference k1 = new Konference("Konference 2025", "Konferencevej 1", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(2), org);
        Konference k2 = new Konference("Workshop 2025", "Workshoppens adresse", LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(2).plusDays(1), org);
        konferences.add(k1);
        konferences.add(k2);

        // (optional) attach hotels to konference if desired
        k1.addHotel(hotel1);
        k1.addHotel(hotel1D);
        k2.addHotel(hotel2);
        k2.addHotel(hotel2D);

        // Sample Udflugts for k1
        Udflugt u1 = new Udflugt("Bytur i Odense", LocalDate.now().plusMonths(1).plusDays(1), 100, true, k1);
        Udflugt u2 = new Udflugt("Kunstmuseum", LocalDate.now().plusMonths(1).plusDays(2), 150, false, k1);
        Udflugt u3 = new Udflugt("Havnerundfart", LocalDate.now().plusMonths(2).plusDays(1), 120, true, k1);

        // Sample Udflugts for k2
        Udflugt u4 = new Udflugt("Teambuilding i skoven", LocalDate.now().plusMonths(2).plusDays(1), 80, false, k2);
        Udflugt u5 = new Udflugt("Middag på lokal restaurant", LocalDate.now().plusMonths(2).plusDays(1), 200, true, k2);
    }

    public static void addDeltager(Deltager d) {
        if (d != null && !deltagere.contains(d)) {
            deltagere.add(d);
        }
    }

    public static List<Deltager> getDeltagere() {
        return Collections.unmodifiableList(deltagere);
    }

    public static List<Hotel> getHotels() {
        return Collections.unmodifiableList(hotels);
    }

    public static List<Konference> getKonferences() {
        return Collections.unmodifiableList(konferences);
    }

    public static List<Udflugt> getUdflugts() {
        return Collections.unmodifiableList(udflugts);
    }

    public static void clearAll() {
        deltagere.clear();
        hotels.clear();
        konferences.clear();
    }
}
