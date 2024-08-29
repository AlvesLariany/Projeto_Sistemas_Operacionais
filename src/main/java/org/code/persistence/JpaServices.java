package org.code.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.code.gui.util.ResponseObject;
import org.code.model.entities.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JpaServices {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit-database");

    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static Map<Integer, ResponseObject> findByEmail(String email, String password) {
        User user = entityManager.find(User.class, email);

        Map<Integer, ResponseObject> response = new LinkedHashMap<>();

        if (user != null) {
            response.put(2, new ResponseObject(true, "Usuário está cadastrado, "));
            if (user.getPassword().equals(password)) {
                response.put(1, new ResponseObject(true, "senha está correta"));
            }
            else {
                response.put(1, new ResponseObject(false, "senha está incorreta"));
            }
        }
        else {
            response.put(2, new ResponseObject(false, "Usuário não encontrado"));
        }
        return response;
    }

    public static  <T> boolean saveItem(T object) {
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

    public static void closeJpaService() {
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
