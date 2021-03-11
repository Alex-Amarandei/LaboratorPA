/**
 * @author Alex Amarandei
 */

// Optional Task #4

package optional;

import java.util.Arrays;
import java.util.Map;

public class TravelPlan {
    private static final int INF = Integer.MAX_VALUE / 2;
    private City city;
    private int[][] preferences;

    public TravelPlan(City city) {
        this.city = city;
        int size = this.city.getLocations().size();
        this.preferences = new int[size][size];

        for (int i = 0; i < size; i++)
            Arrays.fill(preferences[i], INF);

        for (int i = 0; i < size; i++) {
            Location l1 = this.city.getLocations().get(i);
            for (Map.Entry<Location, Integer> l2 : l1.getMap().entrySet()) {
                preferences[this.city.getLocations().indexOf(l1)][this.city.getLocations().indexOf(l2.getKey())] = l2.getValue();
            }
        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int[][] getPreferences() {
        return preferences;
    }

    public void setPreferences(int[][] preferences) {
        this.preferences = preferences;
    }
}
