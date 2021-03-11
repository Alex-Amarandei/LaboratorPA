/**
 * @author Alex Amarandei
 */

package optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Location implements Comparable<Location> {
    protected String name;
    protected String description;
    Map<Location, Integer> map = new HashMap<>();

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Location, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Location, Integer> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getName(), location.getName()) && Objects.equals(getDescription(), location.getDescription()) && Objects.equals(getMap(), location.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getMap());
    }

    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public abstract String toString();
}
