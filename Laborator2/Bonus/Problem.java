/**
 * @author Alex Amarandei
 */

package bonus;

/**
 * A class that models instances of the Transportation Cost problem.
 */
public class Problem {
    private Source[] sources;
    private Destination[] destinations;
    private int[] supply;
    private int[] demand;
    private int[][] cost;

    /**
     * Default constructor that sets all fields to null.
     */
    public Problem() {
        this.sources = null;
        this.destinations = null;
        this.supply = null;
        this.demand = null;
        this.cost = null;
    }

    /**
     * The constructor of an instance of the Transportation Cost Problem.
     *
     * @param sources      are the Sources provided in the text.
     * @param destinations are the Destinations provided in the text.
     * @param supply       are the corresponding supplies for each of the sources provided.
     * @param demand       are the corresponding demands for each of the destinations provided.
     * @param cost         cost[i][j] represents the cost of transporting a unit from source i to destination j.
     */
    public Problem(Source[] sources, Destination[] destinations, int[] supply, int[] demand, int[][] cost) {
        this.sources = checkForDuplicates(sources);
        this.destinations = checkForDuplicates(destinations);
        this.supply = supply;
        this.demand = demand;
        this.cost = cost;
    }

    /**
     * Sources getter.
     *
     * @return the sources provided in the problem.
     */
    public Source[] getSources() {
        return sources;
    }

    /**
     * Optional task #1 - do not allow duplicates.
     *
     * @param sources an array of potential sources which has to meet the following conditions:
     *                1) contains no duplicates.
     *                2) has the same size as the current array (if it exists).
     */
    public void setSources(Source[] sources) {
        if (this.sources == null) {
            this.sources = checkForDuplicates(sources);
            return;
        }
        if (this.sources.length == sources.length && checkForDuplicates(sources) != null)
            System.arraycopy(sources, 0, this.sources, 0, this.sources.length);
    }

    /**
     * Destinations getter.
     *
     * @return the destinations provided in the problem.
     */
    public Destination[] getDestinations() {
        return destinations;
    }

    /**
     * Optional task #1 - do not allow duplicates
     *
     * @param destinations an array of potential destinations which has to meet the following conditions:
     *                     1) contains no duplicates.
     *                     2) has the same size as the current array (if it exists).
     */
    public void setDestinations(Destination[] destinations) {
        if (this.destinations == null) {
            this.destinations = checkForDuplicates(destinations);
            return;
        }
        if (this.destinations.length == destinations.length && checkForDuplicates(destinations) != null)
            System.arraycopy(destinations, 0, this.destinations, 0, this.destinations.length);
    }

    /**
     * Supplies getter.
     *
     * @return the supplies provided in the problem.
     */
    public int[] getSupply() {
        return supply;
    }

    /**
     * Supplies setter.
     *
     * @param supply the supplies for each source
     */
    public void setSupply(int[] supply) {
        this.supply = supply;
    }

    /**
     * Demands getter.
     *
     * @return the demands provided in the problem.
     */
    public int[] getDemand() {
        return demand;
    }

    /**
     * Demands setter.
     *
     * @param demand the demands of each destination
     */
    public void setDemand(int[] demand) {
        this.demand = demand;
    }

    /**
     * Costs getter.
     *
     * @return the costs provided in the problem.
     */
    public int[][] getCost() {
        return cost;
    }

    /**
     * Costs setter.
     *
     * @param cost the transportation cost matrix.
     *             cost[i][j] represents the cost of transporting a unit from source i to destination j.
     */
    public void setCost(int[][] cost) {
        this.cost = cost;
    }

    /**
     * @return A literal description of the problem given.
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("\nThe data provided is:\n\n");

        description.append("There are ");
        description.append(sources.length);
        description.append(" sources: ");

        for (Source i : sources) {
            description.append(i.getName());
            description.append(" ");
        }

        description.append("\nThere are ");
        description.append(destinations.length);
        description.append(" destinations: ");

        for (Destination i : destinations) {
            description.append(i.getName());
            description.append(" ");
        }

        description.append("\n\n");

        for (int i = 0; i < sources.length; i++) {
            description.append(sources[i]);
            description.append(" has a total supply of ");
            description.append(supply[i]);
            description.append(" units and can transport a unit to:\n");

            for (int j = 0; j < destinations.length; j++) {
                description.append("     ");
                description.append(destinations[j]);
                description.append(" at a cost of ");
                description.append(cost[i][j]);
                description.append("\n");
            }

            description.append("\n");
        }

        for (int i = 0; i < destinations.length; i++) {
            description.append(destinations[i]);
            description.append(" has a demand of ");
            description.append(demand[i]);
            description.append(" units.\n");
        }

        return description.toString();
    }

    /**
     * @param array sources array to be checked for duplicates
     * @return null if there are duplicates and the actual array if not
     */
    private Source[] checkForDuplicates(Source[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    System.out.println("Tried adding duplicate source. Sources array set to null.");
                    return null;
                }
            }
        }

        return array;
    }

    /**
     * @param array destinations array to be checked for duplicates
     * @return null if there are duplicates and the actual array if not
     */
    private Destination[] checkForDuplicates(Destination[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    System.out.println("Tried adding duplicate destination. Destinations array set to null.");
                    return null;
                }
            }
        }

        return array;
    }
}
