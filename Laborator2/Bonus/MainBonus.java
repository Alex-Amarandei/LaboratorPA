/**
 * @author Alex Amarandei
 */

package bonus;

/**
 * The Main class for all of the Optional tasks.
 */
public class MainBonus {
    /**
     * The place where everything is assembled.
     *
     * @param args arguments received from the command line
     */
    public static void main(String[] args) {

        Source[] exampleSources = new Source[3]; // Sources specified in the problem
        exampleSources[0] = new Factory("S1");
        exampleSources[1] = new Warehouse("S2");
        exampleSources[2] = new Warehouse("S3");

        Destination[] exampleDestinations = new Destination[3]; // Destinations specified in the problem
        exampleDestinations[0] = new Destination("D1");
        exampleDestinations[1] = new Destination("D2");
        exampleDestinations[2] = new Destination("D3");

        int[] exampleSupplies = new int[3]; // Supplies specified in the problem
        exampleSupplies[0] = 10;
        exampleSupplies[1] = 35;
        exampleSupplies[2] = 25;

        int[] exampleDemands = new int[3]; // Demands specified in the problem
        exampleDemands[0] = 20;
        exampleDemands[1] = 25;
        exampleDemands[2] = 25;

        int[][] exampleCosts = new int[3][3]; // Costs specified in the problem
        exampleCosts[0][0] = 2;
        exampleCosts[0][1] = 3;
        exampleCosts[0][2] = 1;
        exampleCosts[1][0] = 5;
        exampleCosts[1][1] = 4;
        exampleCosts[1][2] = 8;
        exampleCosts[2][0] = 5;
        exampleCosts[2][1] = 6;
        exampleCosts[2][2] = 8;

        Problem problem = new Problem(exampleSources, exampleDestinations, exampleSupplies, exampleDemands, exampleCosts);

        System.out.println(problem);

        // Bonus Task #1 - use Vogel Approximation Method to minimize total cost.

        Algorithm solver = new Algorithm();
        Solution exampleSolution = solver.vogelMethod(problem);

        System.out.println(exampleSolution);

        // Bonus Task #2 - generate large random problem instances.

        Problem randomlyGenerated = ProblemGenerator.generateProblemWith(1500, 1000);

        // System.out.println(randomlyGenerated);
        // the comment above reduces GC calls by a number of thousands

        Solution randomSolution = solver.vogelMethod(randomlyGenerated);
        System.out.println(randomSolution);
    }
}
