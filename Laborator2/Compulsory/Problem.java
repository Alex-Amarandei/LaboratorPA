/*
 * @author Alex Amarandei
 */

package compulsory;

import java.util.Arrays;

public class Problem {
    private Source[] sources;
    private Destination[] destinations;
    private int[] supply;
    private int[] demand;
    private int[][] cost;

    public Problem() {
        this.sources = null;
        this.destinations = null;
        this.supply = null;
        this.demand = null;
        this.cost = null;
    }

    public Problem(Source[] sources, Destination[] destinations, int[] supply, int[] demand, int[][] cost) {
        this.sources = sources;
        this.destinations = destinations;
        this.supply = supply;
        this.demand = demand;
        this.cost = cost;
    }

    public Source[] getSources() {
        return sources;
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
    }

    public Destination[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Destination[] destinations) {
        this.destinations = destinations;
    }

    public int[] getSupply() {
        return supply;
    }

    public void setSupply(int[] supply) {
        this.supply = supply;
    }

    public int[] getDemand() {
        return demand;
    }

    public void setDemand(int[] demand) {
        this.demand = demand;
    }

    public int[][] getCost() {
        return cost;
    }

    public void setCost(int[][] cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        System.out.print("          | ");

        for(int i = 0; i < destinations.length; i++)
            System.out.printf("%6s     | ", destinations[i]);
        System.out.printf("%10s\n", "Supply");

        for(int i = 0; i < sources.length; i++) {
            System.out.printf("%6s    | ", sources[i]);
            for (int j = 0; j < destinations.length; j++)
                System.out.printf("%6d     | ", cost[i][j]);
            System.out.printf("%10d\n", supply[i]);
        }

        System.out.printf("%6s    | ", "Demand");

        for (int i = 0; i < destinations.length; i++)
            System.out.printf("%6d     | ", demand[i]);
        return "\n";
    }
}
