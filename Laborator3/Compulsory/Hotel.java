package compulsory;

public class Hotel extends Location implements Classifiable {
    String rank;
    boolean hasPool;

    public Hotel(String name, String description, String rank, boolean hasPool) {
        super(name, description);
        this.rank = rank;
        this.hasPool = hasPool;
    }
}
