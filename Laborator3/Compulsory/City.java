package compulsory;

import java.util.ArrayList;
import java.util.List;

public class City {
    private final String name;
    private List<Location> locations;

    public City(String name) {
        this.name = name;
    }

    public void addLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }
}
