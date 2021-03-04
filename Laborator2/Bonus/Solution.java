/**
 * @author Alex Amarandei
 */

package bonus;

import java.util.ArrayList;
import java.util.List;

/**
 * Optional Task #3 - create a Solution class
 * The class contains an array of Solution Pairs describing a possible solution to a problem.
 */

public class Solution {
    private List<SolutionPair> solution = new ArrayList<SolutionPair>();

    /**
     * @param pair Source that sends Units to a Destination for a certain Cost
     */
    public void addSolutionPair(SolutionPair pair) {
        this.solution.add(pair);
    }

    /**
     * @return the total cost of the solution provided
     * Is computed by adding the costs involved in each Solution Pair.
     */
    public int getTotalCost() {
        int sum = 0;

        for (SolutionPair pair : solution) {
            sum += pair.getCost() * pair.getQuantity();
        }

        return sum;
    }

    /**
     * @return A literal description of the solution provided.
     */
    @Override
    public String toString() {
        StringBuilder overview = new StringBuilder("\nThe solution is as follows:\n\n");

        for (SolutionPair pair : solution) {
            overview.append("Source ");
            overview.append(pair.getSource().getName());
            overview.append(" sent ");
            overview.append(pair.getQuantity());
            overview.append(" units to Destination ");
            overview.append(pair.getDestination().getName());
            overview.append(" at a cost of ");
            overview.append(pair.getCost());
            overview.append(" totaling ");
            overview.append(pair.getCost() * pair.getQuantity());
            overview.append("\n");
        }

        overview.append("Total Cost is: ");
        overview.append(this.getTotalCost());
        overview.append("\n");

        return overview.toString();
    }

    /**
     * The pairs are composed of a source and a destination.
     * Along are the quantity sent and the cost of sending a unit from the source to the destination.
     */
    static class SolutionPair {
        Destination destination;
        Source source;
        int quantity;
        int cost;

        public SolutionPair(Destination destination, Source source, int quantity, int cost) {
            this.destination = destination;
            this.source = source;
            this.quantity = quantity;
            this.cost = cost;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }
    }
}
