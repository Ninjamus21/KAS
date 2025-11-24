package Storage;

import Model.Hotel;

public class Storage {
    public static void initStorage() {
        Hotel hotel1 = new Hotel("Hotel Scandinavia", "HC Andersen boulevarden 44", 250, false);
        Hotel hotel1D = new Hotel("Hotel Scandinavia", "HC Andersen boulevarden 44", 250, true);
        Hotel hotel2 = new Hotel("Hotel Odense", "Jernbanegade 22", 200, false);
        Hotel hotel2D = new Hotel("Hotel Odense", "Jernbanegade 22", 200, true);
        Hotel hotel3 = new Hotel("Hotel Faaborg", "Nørregade 12", 150, true);
        Hotel hotel4 = new Hotel("Hotel Svendborg", "Langelandsvej 5", 180, false);
        Hotel hotel5 = new Hotel("Hotel Nyborg", "Storebæltsvej 1", 220, true);
        Hotel hotel6 = new Hotel("Hotel Middelfart", "Færgevej 10", 190, false);
        Hotel hotel7 = new Hotel("Hotel Kerteminde", "Havnegade 8", 160, true);
    }
}
