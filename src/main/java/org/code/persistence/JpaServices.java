package org.code.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaServices {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public JpaServices() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("unit-database");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public <T> boolean saveItem(T object) {
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

    public void closeJpaService() {
        try {
            if (entityManagerFactory != null && entityManager != null) {
                this.entityManagerFactory.close();
                this.entityManager.close();
            }
            else {
                throw new NullPointerException();
            }
        } catch (NumberFormatException error) {
            System.out.println("Error in close services JPA: " + error.getMessage());
        }
    }

}
