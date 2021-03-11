/**
 * @author Alex Amarandei
 */

package optional;

import java.util.*;

public class Algorithm {
    static final int INF = Integer.MAX_VALUE / 2;

    // Optional Task #5 - efficient algorithm to determine the shortest path between two given locations, conforming to the preferences

    static void Dijkstra(TravelPlan travelPlan, Location start, Location finish) {
        /*
         * Set<Integer> S = new TreeSet<>();
         * not needed as the set is only updated, but not queried
         */
        Set<Integer> V = new TreeSet<>();
        List<Location> locations = travelPlan.getCity().getLocations();

        int[][] preferences = travelPlan.getPreferences();
        int startIndex = locations.indexOf(start);
        int finishIndex = locations.indexOf(finish);

        int[] before = new int[locations.size()];
        int[] distanceTo = new int[before.length];

        Arrays.fill(distanceTo, INF);

        // S.add(startIndex);
        before[startIndex] = -1;
        distanceTo[startIndex] = 0;
        for (int index = 0; index < before.length; index++) {
            if (index == startIndex) continue;
            V.add(index);
        }

        for (Integer locationIndex : V) {
            distanceTo[locationIndex] = preferences[startIndex][locationIndex];
            before[locationIndex] = startIndex;
        }

        while (!V.isEmpty()) {
            int nearestLocationIndex = -1;
            int minimumDistance = INF;

            for (Integer index : V) {
                if (distanceTo[index] < minimumDistance) {
                    minimumDistance = distanceTo[index];
                    nearestLocationIndex = index;
                }
            }

            // S.add(nearestLocationIndex);
            V.remove(nearestLocationIndex);

            for (Integer i : V) {
                if (distanceTo[i] > distanceTo[nearestLocationIndex] + preferences[nearestLocationIndex][i]) {
                    distanceTo[i] = distanceTo[nearestLocationIndex] + preferences[nearestLocationIndex][i];
                    before[i] = nearestLocationIndex;
                }
            }
        }

        StringBuilder message = new StringBuilder("The shortest path between ");
        message.append(start);
        message.append(" and ");
        message.append(finish);
        message.append(" is: ");

        Deque<Location> stack = new ArrayDeque<>();

        int current = finishIndex;
        while (before[current] != -1) {
            stack.addFirst(locations.get(current));
            current = before[current];
        }
        stack.addFirst(locations.get(current));

        message.append(stack.remove());
        while (!stack.isEmpty()) {
            message.append(" -> ");
            message.append(stack.remove());
        }

        message.append(".\nThe total cost is ");
        message.append(distanceTo[finishIndex]);
        message.append(".");

        System.out.println(message.toString());
    }
}
