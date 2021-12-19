package enumuration;

/**
 * @author Negin Mousavi
 */
public enum CityValue {
    TEHRAN(2.5),
    ISFAHAN(2.3),
    RASHT(1.6),
    KARAJ(1.9),
    SHIRAZ(3.4),
    MAZANDARAN(1.75),
    AHVAZ(4.2),
    NOT_SET(4.5);
    double abbr;

    CityValue(double abbr) {
        this.abbr = abbr;
    }

    public static double getAbbrByValue(CityValue value) {
        return value.abbr;
    }
}
