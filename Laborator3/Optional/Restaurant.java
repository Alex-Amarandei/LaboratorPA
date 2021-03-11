/**
 * @author Alex Amarandei
 */

package optional;

import java.time.LocalTime;

public class Restaurant extends Location implements Classifiable, Visitable {
    private int rank;
    private LocalTime openingHours;
    private LocalTime closingHours;
    private boolean doesDelivery;

    public Restaurant(String name, String description, int rank, boolean doesDelivery) {
        super(name, description);
        this.rank = rank;
        this.doesDelivery = doesDelivery;
    }

    @Override
    public int getRank() {
        return rank;
    }

    // Getters for Opening and Ending Hours left out intentionally to test Location's default methods

    @Override
    public String toString() {
        return this.name;
    }
}
