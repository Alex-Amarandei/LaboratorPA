/**
 * @author Alex Amarandei
 */

package optional.exceptions;

public class InvalidCatalogException extends CatalogException {
    public InvalidCatalogException(String message) {
        super(message);
    }

    public InvalidCatalogException(String message, Exception exception) {
        super(message, exception);
    }
}
