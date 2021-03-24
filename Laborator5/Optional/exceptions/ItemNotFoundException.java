/**
 * @author Alex Amarandei
 */

package optional.exceptions;

public class ItemNotFoundException extends CatalogException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
