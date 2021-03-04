package compulsory;

public class Restaurant extends Location implements Visitable {
    Time openingHours;
    Time endingHours;
    boolean hasVeganOptions;

    public Restaurant(String name, String description, Time openingHours, Time endingHours, boolean hasVeganOptions) {
        super(name, description);
        this.openingHours = openingHours;
        this.endingHours = endingHours;
        this.hasVeganOptions = hasVeganOptions;
    }
}
