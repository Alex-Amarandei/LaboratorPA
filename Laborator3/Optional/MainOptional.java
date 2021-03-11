/**
 * @author Alex Amarandei
 */

package optional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MainOptional {
    public static void main(String[] args) {
        City city = new City("City");

        Hotel v1 = new Hotel("Hotel", "Tourist-Friendly", 5);
        Museum v2 = new Museum("Museum A", "Children-Friendly", true, LocalTime.of(10, 30), LocalTime.of(21, 30));
        Museum v3 = new Museum("Museum B", "Pet-Friendly", false, LocalTime.of(9, 0), LocalTime.of(20, 0));
        v3.setTicketPrice(12.5);
        Church v4 = new Church("Church A", "Catholic-Friendly", "Orthodox", LocalTime.of(8, 0), LocalTime.of(19, 0));
        Church v5 = new Church("Church B", "Greatest Mosque Around", "Muslim", LocalTime.of(7, 0), LocalTime.of(20, 0));
        Restaurant v6 = new Restaurant("Restaurant", "Vegan-Friendly", 4, true);
        // times left out intentionally to check default methods

        v1.setMap(new HashMap<>() {{
            put(v2, 10);
            put(v3, 50);
        }});

        v2.setMap(new HashMap<>() {{
            put(v3, 20);
            put(v4, 20);
            put(v5, 10);
        }});

        v3.setMap(new HashMap<>() {{
            put(v2, 20);
            put(v4, 20);
        }});

        v4.setMap(new HashMap<>() {{
            put(v5, 30);
            put(v6, 10);
        }});

        v5.setMap(new HashMap<>() {{
            put(v4, 30);
            put(v6, 20);
        }});

        city.setLocations(new ArrayList<>() {{
            add(v1);
            add(v2);
            add(v3);
            add(v4);
            add(v5);
            add(v6);
        }});

        System.out.println(city);

        // Optional Task #1 - method to display the locations that are visitable and are not payable, sorted by their opening hour

        city.getVisitableNotPayable();

        // Optional Task #2 - default methods in the interface Visitable

        System.out.println("\n" + v6 + " opens at " + v6.getOpeningHours().getHour() + ":" + v6.getOpeningHours().getMinute() + ".\n");

        // Optional Task #4 - a TravelPlan instance will contain a city and the preferences regarding the visiting order

        TravelPlan travelPlan = new TravelPlan(city);

        // Optional Task #5 - efficient algorithm to determine the shortest path between two given locations, conforming to the preferences

        Algorithm.Dijkstra(travelPlan, v1, v6);
    }
}
