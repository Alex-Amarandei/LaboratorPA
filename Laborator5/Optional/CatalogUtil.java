/**
 * @author Alex Amarandei
 */

package optional;

import optional.exceptions.InvalidCatalogException;

import java.io.*;

public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path) throws InvalidCatalogException {
        Catalog catalog = new Catalog();
        try (FileInputStream streamIn = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(streamIn)) {
            catalog = ((Catalog) objectInputStream.readObject());
        } catch (IOException e) {
            throw new InvalidCatalogException("Error loading catalog from file.", e);
        } catch (ClassNotFoundException e) {
            throw new InvalidCatalogException("Invalid catalog file.", e);
        }

        return catalog;
    }
}
