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
    private static final ArrayList<HotelTilkøb> hotelTilkøbs = new ArrayList<>();

    public static void initStorage() {
        // Hotels
        Hotel hotel1 = new Hotel("Den hvide svane", "HC Andersen boulevarden 44", 1050, false);
        Hotel hotel1D = new Hotel("Den hvide svane - D", "HC Andersen boulevarden 44", 1250, true);
        Hotel hotel2 = new Hotel("Høtel phonix", "Jernbanegade 22", 700, false);
        Hotel hotel2D = new Hotel("Høtel phonix - D", "Jernbanegade 22", 800, true);
        Hotel hotel3 = new Hotel("Pension tusindfryd", "Nørregade 12", 500, false);
        Hotel hotel3D = new Hotel("Pension tusindfryd - D", "Nørregade 12", 600, true);
        hotels.add(hotel1);
        hotels.add(hotel1D);
        hotels.add(hotel2);
        hotels.add(hotel2D);
        hotels.add(hotel3);

        // Sample Konference + organisation
        KonferenceOrganisation org = new KonferenceOrganisation("Default Org");
        Konference k1 = new Konference("Hav og Himmel", "Konferencevej 1", LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1).plusDays(2), org);
        Konference k2 = new Konference("Workshop 2025", "Workshoppens adresse", LocalDate.now().plusMonths(2), LocalDate.now().plusMonths(2).plusDays(1), org);
        konferences.add(k1);
        konferences.add(k2);

        // (optional) attach hotels to konference if desired
        k1.addHotel(hotel1);
        k1.addHotel(hotel1D);
        k1.addHotel(hotel2);
        k1.addHotel(hotel2D);
        k1.addHotel(hotel3);
        k1.addHotel(hotel3D);

        // Sample Udflugts for k1
        Udflugt u1 = new Udflugt("Byrundtur Odense", LocalDate.now().plusMonths(1).plusDays(1), 125, true, k1);
        Udflugt u2 = new Udflugt("Egeskov", LocalDate.now().plusMonths(1).plusDays(2), 75, false, k1);
        Udflugt u3 = new Udflugt("Trapholt", LocalDate.now().plusMonths(2).plusDays(1), 200, true, k1);

        // Sample Udflugts for k2
        Udflugt u4 = new Udflugt("Teambuilding i skoven", LocalDate.now().plusMonths(2).plusDays(1), 80, false, k2);
        Udflugt u5 = new Udflugt("Middag på lokal restaurant", LocalDate.now().plusMonths(2).plusDays(1), 200, true, k2);

        udflugts.add(u1);
        udflugts.add(u2);
        udflugts.add(u3);
        udflugts.add(u4);
        udflugts.add(u5);

        // Sample HotelTilkøb
        HotelTilkøb ht1 = new HotelTilkøb("Wifi", 50);
        HotelTilkøb ht2 = new HotelTilkøb("Wifi", 75);
        HotelTilkøb ht3 = new HotelTilkøb("Bad", 200);
        HotelTilkøb ht4 = new HotelTilkøb("Morgenmad", 100);
        hotel1.createHotelTilkøb(ht1.getTillægNavn(), ht1.getTillægPris());
        hotel1D.createHotelTilkøb(ht1.getTillægNavn(), ht1.getTillægPris());

        hotel2.createHotelTilkøb(ht2.getTillægNavn(), ht2.getTillægPris());
        hotel2D.createHotelTilkøb(ht2.getTillægNavn(), ht2.getTillægPris());

        hotel2.createHotelTilkøb(ht3.getTillægNavn(), ht3.getTillægPris());
        hotel2D.createHotelTilkøb(ht3.getTillægNavn(), ht3.getTillægPris());

        hotel3.createHotelTilkøb(ht3.getTillægNavn(), ht3.getTillægPris());
        hotel3D.createHotelTilkøb(ht3.getTillægNavn(), ht3.getTillægPris());

        hotelTilkøbs.add(ht1);
        hotelTilkøbs.add(ht2);
        hotelTilkøbs.add(ht3);
        hotelTilkøbs.add(ht4);
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

    public static ArrayList<Konference> getKonferencer() {
        return konferences;
    }
}
