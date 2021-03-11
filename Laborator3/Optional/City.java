/**
 * @author Alex Amarandei
 */

package optional;

import java.util.*;

public class City {
    private String name;
    private List<Location> locations = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(getName(), city.getName()) && Objects.equals(getLocations(), city.getLocations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLocations());
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("The City of ");
        toPrint.append(name);
        toPrint.append(" prides itself with the following locations:\n\n");
        for (int i = 0; i < locations.toArray().length; i++) {
            toPrint.append(locations.toArray()[i].toString());
            toPrint.append("\n");
        }

        return toPrint.toString();
    }

    // Optional Task #1 - method to display the locations that are visitable and are not payable, sorted by their opening hour

    public void getVisitableNotPayable() {
        int length = this.locations.toArray().length;
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            if (this.locations.get(i) instanceof Visitable && !(this.locations.get(i) instanceof Payable))
                locations.add(this.locations.get(i));
        }

        length = locations.size();
        System.out.println("The following locations are visitable and not payable:");
        Collections.sort(locations, Comparator.comparing(o -> ((Visitable) o).getOpeningHours()));
        for (int i = 0; i < length; i++) {
            System.out.println(locations.get(i).getName());
        }
    }
}
