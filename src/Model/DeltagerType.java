package Model;

public enum DeltagerType {
    ANSAT(0),
    STUDERENDE(400),
    ARBEJDSLØS(500),
    PENSTIONIST(300),
    FORDRAGSHOLDER(0);

    private final double pris;

    DeltagerType(double pris) {
        this.pris = pris;
    }

    public double getPris() {
        return pris;
    }
}
