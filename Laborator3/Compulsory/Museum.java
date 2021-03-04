package compulsory;

public class Museum extends Location implements Visitable, Payable {
    Time openingHours;
    Time endingHours;
    double tax;
    String area;

    public Museum(String name, String description, Time openingHours, Time endingHours, double tax, String area) {
        super(name, description);
        this.openingHours = openingHours;
        this.endingHours = endingHours;
        this.tax = tax;
        this.area = area;
    }
}
