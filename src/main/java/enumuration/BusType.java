package enumuration;

/**
 * @author Negin Mousavi
 */
public enum BusType {
    VIP(5.0),
    REGULAR(2.0);

    double abbr;

    BusType(double abbr) {
        this.abbr = abbr;
    }

    public static double getAbbrByValue(BusType value) {
        return value.abbr;
    }
}
