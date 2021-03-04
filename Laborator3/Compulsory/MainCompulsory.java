/**
 * @author Alex Amarandei
 */

package compulsory;

import java.util.ArrayList;
import java.util.HashMap;

public class MainCompulsory {
    public static void main (String[] args) {
        City city = new City("City");

        Hotel v1 = new Hotel("Hotel", "Tourist-Friendly", 5);
        Museum v2 = new Museum("Museum A", "Children-Friendly", true);
        Museum v3 = new Museum("Museum B", "Pet-Friendly", false);
        Church v4 = new Church("Church A", "Catholic-Friendly", "Orthodox");
        Church v5 = new Church("Church B", "Greatest Mosque Around", "Muslim");
        Restaurant v6 = new Restaurant("Restaurant", "Vegan-Friendly", 4, true);

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
    }
}
