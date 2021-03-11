/**
 * @author Alex Amarandei
 */

package optional;

import java.time.LocalTime;

public class Church extends Location implements Visitable {
    private final String religion;
    private LocalTime openingHours;
    private LocalTime closingHours;

    public Church(String name, String description, String religion, LocalTime openingHours, LocalTime closingHours) {
        super(name, description);
        this.religion = religion;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
    }

    @Override
    public LocalTime getOpeningHours() {
        return openingHours;
    }

    @Override
    public LocalTime getClosingHours() {
        return closingHours;
    }

    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("The ");
        toPrint.append(religion);
        toPrint.append(" ");
        toPrint.append(name);
        return toPrint.toString();
    }
}
