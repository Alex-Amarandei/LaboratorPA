/**
 * @author Alex Amarandei
 */

package optional;

import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {
    // Optional Task #2 - default methods in the interface Visitable

    static Duration getVisitingDuration(Location location) {
        if (location instanceof Visitable) {
            return Duration.between(((Visitable) location).getOpeningHours(), ((Visitable) location).getClosingHours());
        }

        return Duration.ZERO;
    }

    default LocalTime getOpeningHours() {
        return LocalTime.of(9, 30);
    }

    // Optional Task #3 - static method returning how long a location is opened during a day

    default LocalTime getClosingHours() {
        return LocalTime.of(20, 0);
    }
}
