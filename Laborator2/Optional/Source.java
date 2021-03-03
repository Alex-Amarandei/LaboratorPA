/**
 * @author Alex Amarandei
 */

package optional;

import java.util.Objects;

/**
 * Optional Task #2 - create an abstract Source class
 * Will be the model for the Factory and Warehouse classes
 */
public abstract class Source {
    /**
     * The source's name.
     */
    protected String name;

    /**
     * Constructor taking a name for the source.
     * @param name the name of the source.
     */
    public Source(String name) {
        this.name = name;
    }

    /**
     * Name getter.
     * @return The source's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter.
     * @param name The source's desired name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the source's name preceded by its type.
     */
    @Override
    public abstract String toString();

    /**
     * Optional Task #1 - override the equals method
     * @param o the object to be compared to
     * @return true if the objects are the same and false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return Objects.equals(getName(), source.getName());
    }
}
