/**
 * @author Alex Amarandei
 */

package bonus;

/**
 * Optional Task #2 - create dedicated classes
 * Factory is a Source Type.
 */
public class Factory extends Source {

    /**
     * Constructor calling the Source class' constructor.
     *
     * @param name the factory's desired name.
     */
    public Factory(String name) {
        super(name);
    }

    /**
     * @return the factory's name preceded by "Factory:".
     */
    @Override
    public String toString() {
        StringBuilder nameString = new StringBuilder("Factory: ");
        nameString.append(name);

        return nameString.toString();
    }
}
