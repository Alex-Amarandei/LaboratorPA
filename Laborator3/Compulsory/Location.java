package compulsory;

import java.util.HashMap;
import java.util.Map;

public abstract class Location {
    protected String name;
    protected String description;
    protected Map<Location, Integer> map;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.map = new HashMap<>();
    }

    public void addMap(HashMap<Location, Integer> map) {
        this.map = map;
    }
}
