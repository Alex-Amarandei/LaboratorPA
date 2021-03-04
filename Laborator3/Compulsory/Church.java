package compulsory;

public class Church extends Location implements Visitable {
    Time openingHours;
    Time endingHours;
    String religion;

    public Church(String name, String description, Time openingHours, Time endingHours, String religion) {
        super(name, description);
        this.openingHours = openingHours;
        this.endingHours = endingHours;
        this.religion = religion;
    }
}
