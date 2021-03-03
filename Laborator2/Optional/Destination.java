/**
 * @author Alex Amarandei
 */

package optional;

/**
 * The location which the units shall be sent to.
 */
public class Destination {
    private String name;

    /**
     * Constructor which takes a name for the destination.
     * @param name the destination's name
     */
    public Destination(String name) {
        this.name = name;
    }

    /**
     * Name getter.
     * @return the destination's name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name the destination's desired name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the destination's name preceded by "Destination:"
     */
    @Override
    public String toString() {
        StringBuilder nameString = new StringBuilder("Destination ");
        nameString.append(name);

        return nameString.toString();
    }

    /**
     * Optional Task #1 - override the equals method
     * @param o the object to be compared to
     * @return true if the objects are the same and false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination that = (Destination) o;
        return getName().equals(that.getName());
    }
}
