package org.code.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DataService {
    private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit-database");
    private final static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static <T> boolean saveItem(T object) {
        if (object != null) {
            try {
                entityManager.getTransaction().begin();
                {
                    entityManager.persist(object);
                }
                entityManager.getTransaction().commit();

                return true;
            } catch (Exception error) {
                System.out.println("Error in add: " + error.getMessage());

                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            }
        }
        return false;
    }

    public void closeDataService() {
        try {
            if (entityManagerFactory != null && entityManager != null) {
                entityManagerFactory.close();
                entityManager.close();
            }
            else {
                throw new NullPointerException();
            }
        } catch (NumberFormatException error) {
            System.out.println("Error in close services JPA: " + error.getMessage());
        }
    }

}
