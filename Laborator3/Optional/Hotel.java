/**
 * @author Alex Amarandei
 */

package optional;

public class Hotel extends Location implements Classifiable {
    int rank;

    public Hotel(String name, String description, int rank) {
        super(name, description);
        this.rank = rank;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
