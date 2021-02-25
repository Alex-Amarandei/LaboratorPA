package compulsory;

public class MainCompulsory {
    public static void main(String[] args) {

        Problem problem = new Problem();

        Source[] exampleSources = new Source[3]; // Sources specified in the problem
        exampleSources[0] = new Source("S1", SourceType.FACTORY);
        exampleSources[1] = new Source("S2", SourceType.WAREHOUSE);
        exampleSources[2] = new Source("S3", SourceType.WAREHOUSE);
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
        exampleCosts[0][0] = 2; exampleCosts[0][1] = 3; exampleCosts[0][2] = 1;
        exampleCosts[1][0] = 5; exampleCosts[1][1] = 4; exampleCosts[1][2] = 8;
        exampleCosts[2][0] = 5; exampleCosts[2][1] = 6; exampleCosts[2][2] = 8;
        problem.setCost(exampleCosts); // Assigning the costs to the problem problem

        /* Alternatively, we could've declared the problem here instead
         * Problem problem = new Problem(exampleSources, exampleDestinations, exampleSupplies, exampleDemands, exampleCosts);
         */

        System.out.println(problem);
    }
}
