/**
 * @author Alex Amarandei
 */

package optional;

/**
 * The Main class for all of the Optional tasks.
 */
public class MainOptional {
    /**
     * The place where everything is assembled.
     * @param args arguments received from the command line
     */
    public static void main(String[] args) {

        Problem problem = new Problem();

        Source[] exampleSources = new Source[3]; // Sources specified in the problem
        exampleSources[0] = new Factory("S1");
        exampleSources[1] = new Warehouse("S2");
        exampleSources[2] = new Warehouse("S3");
        problem.setSources(exampleSources); // Assigning the sources to the problem problem

        Destination[] exampleDestinations = new Destination[3]; // Destinations specified in the problem
        exampleDestinations[0] = new Destination("D1");
        exampleDestinations[1] = new Destination("D2");
        exampleDestinations[2] = new Destination("D3");
        problem.setDestinations(exampleDestinations); // Assigning the destinations to the problem problem

        int[] exampleSupplies = new int[3]; // Supplies specified in the problem
        exampleSupplies[0] = 10;
        exampleSupplies[1] = 35;
        exampleSupplies[2] = 25;
        problem.setSupply(exampleSupplies); // Assigning the supplies to the problem problem

        int[] exampleDemands = new int[3]; // Demands specified in the problem
        exampleDemands[0] = 20;
        exampleDemands[1] = 25;
        exampleDemands[2] = 25;
        problem.setDemand(exampleDemands); // Assigning the demands to the problem problem

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
        problem.setCost(exampleCosts); // Assigning the costs to the problem problem

        /* Alternatively, we could've declared the problem here instead
         * Problem problem = new Problem(exampleSources, exampleDestinations, exampleSupplies, exampleDemands, exampleCosts);
         */

        System.out.println(problem);

        // Optional task #1 - do not allow duplicates

        // Test: creating a new problem instance with a source array containing duplicates

        Source[] exampleSourcesDuplicate = new Source[3];
        exampleSourcesDuplicate[0] = new Factory("S1");
        exampleSourcesDuplicate[1] = new Warehouse("S2");
        exampleSourcesDuplicate[2] = new Factory("S1");

        Problem exampleSourceDuplicateInConstructor = new Problem(exampleSourcesDuplicate, exampleDestinations, exampleSupplies, exampleDemands, exampleCosts);

        // Test: creating a new problem instance and assigning a destination array containing duplicates

        Destination[] exampleDestinationDuplicate = new Destination[3];
        exampleDestinationDuplicate[0] = new Destination("D1");
        exampleDestinationDuplicate[1] = new Destination("D3");
        exampleDestinationDuplicate[2] = new Destination("D3");

        Problem exampleDestinationDuplicateInSetter = new Problem(exampleSources, exampleDestinations, exampleSupplies, exampleDemands, exampleCosts);

        exampleDestinationDuplicateInSetter.setDestinations(exampleDestinationDuplicate);

        // Optional Task #4 - create a simple algorithm that provides a feasible solution

        Algorithm solver = new Algorithm();
        Solution exampleSolution = solver.greedySupplier(problem);

        System.out.println(exampleSolution);
    }
}
