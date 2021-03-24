/**
 * @author Alex Amarandei
 */

package optional.exceptions;

public abstract class CatalogException extends Exception {
    public CatalogException(String message, Exception exception) {
        super(message, exception);
    }

    public CatalogException(String message) {
        super(message);
    }
}
