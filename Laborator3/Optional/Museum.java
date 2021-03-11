/**
 * @author Alex Amarandei
 */

package optional;

import java.time.LocalTime;

public class Museum extends Location implements Visitable, Payable {
    private LocalTime openingHours;
    private LocalTime closingHours;
    private boolean hasGuidedTours;
    private double ticketPrice;

    public Museum(String name, String description, boolean hasGuidedTours, LocalTime openingHours, LocalTime closingHours) {
        super(name, description);
        this.hasGuidedTours = hasGuidedTours;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
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
    public String toString() {
        return name;
    }
}
