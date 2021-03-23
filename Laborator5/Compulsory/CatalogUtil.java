/**
 * @author Alex Amarandei
 */

package laborator5.compulsory;

import java.io.*;

public class CatalogUtil {
    public static void save(Catalog catalog) {
            try (var oos = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {
                oos.writeObject(catalog);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static Catalog load(String path) {
        ObjectInputStream objectinputstream = null;
        Catalog catalog = new Catalog();
        try {
            FileInputStream streamIn = new FileInputStream(path);
            objectinputstream = new ObjectInputStream(streamIn);
            catalog = ((Catalog) objectinputstream.readObject());
        } catch (Exception e) {
            new InvalidCatalogException(e).printStackTrace();
        } finally {
            if(objectinputstream != null){
                try {
                    objectinputstream.close();
                } catch (IOException e) {
                    new InvalidCatalogException(e).printStackTrace();
                }
            }

            return catalog;
        }
    }

}
