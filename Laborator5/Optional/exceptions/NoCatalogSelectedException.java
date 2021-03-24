/**
 * @author Alex Amarandei
 */

package optional.exceptions;

public class NoCatalogSelectedException extends CatalogException {
    public NoCatalogSelectedException(String message) {
        super(message);
    }
}
