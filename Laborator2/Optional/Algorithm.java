/**
 * @author Alex Amarandei
 */

package optional;

/**
 * A class containing multiple algorithms for solving the Transportation Cost problem.
 */
public class Algorithm {
    private int getMinimumPosition(int[] costs, int[] demand) {
        int minimumValue = Integer.MAX_VALUE;
        int position = -1;

        for (int i = 0; i < costs.length; i++)
            if (costs[i] < minimumValue && demand[i] > 0) {
                minimumValue = costs[i];
                position = i;
            }

        return position;
    }

    /**
     * Optional Task #4 - create a simple algorithm that provides a feasible solution.
     * The algorithm is Greedy from the supplier's point of view.
     * We iterate through the cost matrix and for every supplier we determine the minimum existing cost.
     * We send as many units as possible at that lowest cost to the destination in cause.
     * We subtract the units sent from both the supply and demand.
     * We repeat this until the supply is 0, case in which we go to the next supplier.
     * @param problem the problem to be solved.
     * @return the solution of the Transportation Cost Problem instance given, using a Greedy approach
     */
    public Solution greedySupplier(Problem problem) {
        Source[] sources = problem.getSources();
        Destination[] destinations = problem.getDestinations();
        int[] supply = problem.getSupply();
        int[] demand = problem.getDemand();
        int[][] cost = problem.getCost();
        Solution solution = new Solution();

        for (int i = 0; i < supply.length; i++) {
            while (supply[i] > 0) {
                int minimumPosition = getMinimumPosition(cost[i], demand);

                int quantityToBeSent = Math.min(demand[minimumPosition], supply[i]);
                demand[minimumPosition] -= quantityToBeSent;
                supply[i] -= quantityToBeSent;

                Solution.SolutionPair solutionPair = new Solution.SolutionPair(destinations[minimumPosition], sources[i], quantityToBeSent, cost[i][minimumPosition]);

                solution.addSolutionPair(solutionPair);
            }
        }

        return solution;
    }
}
