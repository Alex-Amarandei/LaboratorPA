package compulsory.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static EntityManagerFactory factory = null;

    private PersistenceUtil() {
    }

    public static EntityManagerFactory getFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("MoviesPU");
        }
        return factory;
    }

}
