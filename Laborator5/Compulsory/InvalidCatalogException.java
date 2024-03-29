/**
 * @author Alex Amarandei
 */

package laborator5.compulsory;

import java.io.IOException;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException(IOException ex) {
        super("Error closing file.", ex);
    }

    public InvalidCatalogException(Exception ex) {
        super("Invalid catalog file.", ex);
    }
}
