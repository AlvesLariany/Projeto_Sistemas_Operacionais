package org.code.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.code.gui.util.Alerts;
import org.code.gui.util.ImageUtil;
import org.code.model.entities.Users;

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

    private static void aplyRollback() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    public static Users findByHashEmail(String emailHash) {
        Users users = null;
        try {
            users = entityManager.find(Users.class, emailHash);
            if (users == null) {
                throw new NullPointerException();
            }
            return users;

        } catch (NullPointerException error) {
            aplyRollback();
            //alerta foi removido daqui
        }
        return null;
    }

    public static Image getImageByHashEmail(String userHashEmail) {
        Users users = findByHashEmail(userHashEmail);

        return ImageUtil.createFileWithPath(users.getImage_path());
    }

    //atualiza o campo do path da imagem com base no hash so email do user
    public static void updateImageByHashEmail(String userHashEmail) {
        String emailHash = userHashEmail;

        try {
                entityManager.getTransaction().begin();
                {
                    Users users = findByHashEmail(emailHash);

                    if (users != null) {
                        users.setImage_path(ImageUtil.createDialogAndGetPath());
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                    //atualizando usuário
                    entityManager.merge(users);
                }
                entityManager.getTransaction().commit();

            Alerts.showAlert("Atualizado", null, "Imagem de perfil foi atualizada com sucesso", Alert.AlertType.CONFIRMATION);

        } catch (IllegalArgumentException error) {
            aplyRollback();
            Alerts.showAlert("Erro", null, ("Falha ao atualizar a imagem da conta"), Alert.AlertType.ERROR);
        }
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
