/**
 * @author Alex Amarandei
 */

package bonus;

/**
 *  This class has only one static method used for generating a random instance of the Transportation Cost problem
 */
public class ProblemGenerator {
    /**
     * The method is static in order to eliminate the need of instantiating just for one functionality
     * @param numberOfSources the number of sources for the instance
     * @param numberOfDestinations the number of destinations for the instance
     * @return a random instance of the Transportation Cost problem
     */
    static Problem generateProblemWith(int numberOfSources, int numberOfDestinations) {
        // creating all the Problem fields needed
        Source[] sources = new Source[numberOfSources];
        int[] supply = new int[numberOfSources];
        Destination[] destinations = new Destination[numberOfDestinations];
        int[] demand = new int[numberOfDestinations];
        int[][] cost = new int[numberOfSources][numberOfDestinations];

        int numberOfUnits = (int) (Math.random() * (20000 - Math.max(numberOfSources, numberOfDestinations)) + Math.max(numberOfSources, numberOfDestinations)); // generating a number of units equal for both the sources and the destinations
        int averageSupplyUnits = numberOfUnits / numberOfSources;
        int averageDemandUnits = numberOfUnits / numberOfDestinations;

        int remainingSupplyUnits = numberOfUnits;
        int remainingDemandUnits = numberOfUnits;

        for (int i = 0; i < numberOfSources - 1; i++) {
            sources[i] = new Warehouse("S" + i);
            supply[i] = (int) (Math.random() * (Math.min(1.25 * averageSupplyUnits, remainingSupplyUnits) - 1) + 1);
            remainingSupplyUnits -= supply[i];
            // populating the supply array and subtracting the current supply from the remaining units
        }

        sources[numberOfSources - 1] = new Warehouse("S" + (numberOfSources - 1));
        supply[numberOfSources - 1] = remainingSupplyUnits;
        // last source takes the remainder

        for (int i = 0; i < numberOfDestinations; i++) {
            destinations[i] = new Destination("D" + i);
            demand[i] = (int) (Math.random() * (Math.min(1.25 * averageDemandUnits, remainingDemandUnits) - 1) + 1);
            remainingDemandUnits -= demand[i];
            // populating the demand array and subtracting the current demand from the remaining units
        }

        destinations[numberOfDestinations - 1] = new Destination("S" + (numberOfDestinations - 1));
        demand[numberOfDestinations - 1] = remainingDemandUnits;
        // last destination takes the remainder

        for (int i = 0; i < numberOfSources; i++) {
            for (int j = 0; j < numberOfDestinations; j++) {
                cost[i][j] = (int) (Math.random() * 50 + 1);
            }
        }

        Problem problem = new Problem(sources, destinations, supply, demand, cost);

        return problem;
    }
}
