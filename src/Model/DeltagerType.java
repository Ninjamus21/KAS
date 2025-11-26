package Model;

public enum DeltagerType {
    ANSAT(0),
    STUDERENDE(1500),
    ARBEJDSLØS(1500),
    PENSTIONIST(1500),
    FORDRAGSHOLDER(0);

    private final double pris;

    DeltagerType(double pris) {
        this.pris = pris;
    }

    public double getPris() {
        return pris;
    }
}
