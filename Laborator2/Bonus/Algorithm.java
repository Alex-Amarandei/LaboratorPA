/**
 * @author Alex Amarandei
 */

package bonus;

import bonus.Solution.SolutionPair;

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
     *
     * @param problem the problem to be solved.
     * @return the solution of the Transportation Cost Problem instance given, using a Greedy approach.
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

    /**
     * Bonus Task #1 - use Vogel Approximation Method to minimize total cost.
     *
     * @param problem the problem to be solved.
     * @return the solution of the Transportation Cost Problem instance given, using the Vogel method.
     */
    public Solution vogelMethod(Problem problem) {
        Source[] sources = problem.getSources();
        Destination[] destinations = problem.getDestinations();
        int[] supply = problem.getSupply();
        int[] demand = problem.getDemand();
        int[][] cost = problem.getCost();
        Solution solution = new Solution();

        int[] rowDifference = new int[sources.length];
        int[] columnDifference = new int[destinations.length];
        int[] leastValues; // used for keeping the two least costs
        boolean[] isRowCancelled = new boolean[sources.length]; // used for keeping track of empty sources
        boolean[] isColumnCancelled = new boolean[destinations.length]; // used for keeping track of empty destinations
        boolean done = false; // will be false when there were no more changes to be made

        while (!done) {
            done = true; // assume that we are done

            for (int i = 0; i < sources.length; i++) { // completing the rowDifference array
                if (!isRowCancelled[i]) {
                    done = false;
                    leastValues = getLeastValues(cost[i], isColumnCancelled);
                    rowDifference[i] = Math.abs(leastValues[0] - leastValues[1]);
                } else rowDifference[i] = -1; // marked with -1 to be ignored while searching max
            }

            for (int j = 0; j < destinations.length; j++) { // completing the columnDifference array
                if (!isColumnCancelled[j]) {
                    int minimum1 = Integer.MAX_VALUE;
                    int minimum2 = Integer.MAX_VALUE;
                    done = false;
                    for (int i = 0; i < sources.length; i++) {
                        if (!isRowCancelled[i]) {
                            if (cost[i][j] < minimum2) {
                                if (cost[i][j] < minimum1) {
                                    minimum2 = minimum1;
                                    minimum1 = cost[i][j];
                                } else {
                                    minimum2 = cost[i][j];
                                }
                            }
                        }
                    }

                    if (minimum1 == Integer.MAX_VALUE) minimum1 = 0;
                    if (minimum2 == Integer.MAX_VALUE) minimum2 = 0;

                    columnDifference[j] = Math.abs(minimum1 - minimum2);
                } else columnDifference[j] = -1; // marked with -1 to be ignored while searching max
            }

            int maxRowPenaltyPosition = getMaxPenaltyPosition(rowDifference);
            int maxColumnPenaltyPosition = getMaxPenaltyPosition(columnDifference);

            if (rowDifference[maxRowPenaltyPosition] > columnDifference[maxColumnPenaltyPosition]) {
                /* if the maximum Penalty is in the rowDifference array
                 * we search its respective column for the least cost
                 */

                int minColumnPosition = 0;
                int minimum = Integer.MAX_VALUE;

                for (int i = 0; i < destinations.length; i++) {
                    if (!isColumnCancelled[i]) {
                        if (cost[maxRowPenaltyPosition][i] < minimum) {
                            minimum = cost[maxRowPenaltyPosition][i];
                            minColumnPosition = i;
                        }
                    }
                }

                // subtract the lesser quantity between the demand and the supply
                int quantity = Math.min(demand[minColumnPosition], supply[maxRowPenaltyPosition]);
                if (quantity == 0) return solution;

                demand[minColumnPosition] -= quantity;
                if (demand[minColumnPosition] == 0)
                    isColumnCancelled[minColumnPosition] = true;
                // if the demand is zero, we cancel the column

                supply[maxRowPenaltyPosition] -= quantity;
                if (supply[maxRowPenaltyPosition] == 0)
                    isRowCancelled[maxRowPenaltyPosition] = true;
                // if the supply is zero, we cancel the row

                SolutionPair solutionPair = new SolutionPair(destinations[minColumnPosition], sources[maxRowPenaltyPosition], quantity, cost[maxRowPenaltyPosition][minColumnPosition]);
                solution.addSolutionPair(solutionPair);
                // we add the current solution to the solution array
            } else {
                /* if the maximum Penalty is in the columnDifference array
                 * we search its respective row for the least cost
                 */

                int minRowPosition = 0;
                int minimum = Integer.MAX_VALUE;

                for (int i = 0; i < sources.length; i++) {
                    if (!isRowCancelled[i]) {
                        if (cost[i][maxColumnPenaltyPosition] < minimum) {
                            minimum = cost[i][maxColumnPenaltyPosition];
                            minRowPosition = i;
                        }
                    }
                }

                // subtract the lesser quantity between the demand and the supply
                int quantity = Math.min(demand[maxColumnPenaltyPosition], supply[minRowPosition]);
                if (quantity == 0) return solution;

                demand[maxColumnPenaltyPosition] -= quantity;
                if (demand[maxColumnPenaltyPosition] == 0)
                    isColumnCancelled[maxColumnPenaltyPosition] = true;
                // if the demand is zero, we cancel the column

                supply[minRowPosition] -= quantity;
                if (supply[minRowPosition] == 0)
                    isRowCancelled[minRowPosition] = true;
                // if the supply is zero, we cancel the row

                SolutionPair solutionPair = new SolutionPair(destinations[maxColumnPenaltyPosition], sources[minRowPosition], quantity, cost[minRowPosition][maxColumnPenaltyPosition]);
                solution.addSolutionPair(solutionPair);
                // we add the current solution to the solution array
            }
        }

        return solution;
    }

    private int getMaxPenaltyPosition(int[] array) {
        int maxValue = Integer.MIN_VALUE;
        int position = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                position = i;
            }
        }

        return position;
    }

    private int[] getLeastValues(int[] row, boolean[] isCancelled) {
        int minimum1 = Integer.MAX_VALUE;
        int minimum2 = Integer.MAX_VALUE;

        for (int i = 0; i < row.length; i++) {
            if (!isCancelled[i]) {
                if (row[i] < minimum2) {
                    if (row[i] < minimum1) {
                        minimum2 = minimum1;
                        minimum1 = row[i];
                    } else {
                        minimum2 = row[i];
                    }
                }
            }
        }

        if (minimum1 == Integer.MAX_VALUE) minimum1 = 0;
        if (minimum2 == Integer.MAX_VALUE) minimum2 = 0;

        return new int[]{minimum1, minimum2};
    }
}
