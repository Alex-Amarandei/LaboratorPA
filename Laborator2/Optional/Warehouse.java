/**
 * @author Alex Amarandei
 */

package optional;

/**
 * Optional Task #2 - create dedicated classes
 * Warehouse is a Source Type.
 */
public class Warehouse extends Source {

    /**
     * Constructor calling the Source class' constructor.
     * @param name the warehouse's desired name.
     */
    public Warehouse(String name) {
        super(name);
    }

    /**
     * @return the warehouse's name preceded by "Warehouse:".
     */
    @Override
    public String toString() {
        StringBuilder nameString = new StringBuilder("Warehouse: ");
        nameString.append(name);

        return nameString.toString();
    }
}
