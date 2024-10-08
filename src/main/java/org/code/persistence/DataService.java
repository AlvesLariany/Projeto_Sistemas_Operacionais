package org.code.persistence;

import jakarta.persistence.*;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.code.gui.util.Alerts;
import org.code.gui.util.ImageUtil;
import org.code.model.entities.Chanel;
import org.code.model.entities.Message;
import org.code.model.entities.Users;
import org.code.model.util.TokenUserUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DataService {
    private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit-database");
    private final static EntityManager entityManager = entityManagerFactory.createEntityManager();


    //usando tipo coringa "<T>" para possibilitar que qualquer entidade aproveite o mesmo método de salvamentoi
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

    public static Chanel findByChanelId(Long id) {
        Chanel chanel = null;
        try {
            chanel = entityManager.find(Chanel.class, id);
            if (chanel == null) {
                throw new NullPointerException();
            }
            return chanel;

        } catch (NullPointerException error) {
            aplyRollback();
            //alerta foi removido daqui
        }
        return null;
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

    public static void updateUserName(String newName) {
        entityManager.getTransaction().begin();
        {
            Users users = findByHashEmail(TokenUserUtil.getUserToken());

            if (users != null) {
                //gerando bytes a partir do path da imagem do usuário
                users.setName(newName);
            }
            else {
                throw new IllegalArgumentException();
            }
            //atualizando usuário
            entityManager.merge(users);
        }
        entityManager.getTransaction().commit();
    }

    //atualiza o campo do path da imagem com base no hash so email do user
    public static void updateImageByHashEmail(String userHashEmail) {
        String emailHash = userHashEmail;

        try {
                entityManager.getTransaction().begin();
                {
                    Users users = findByHashEmail(emailHash);

                    if (users != null) {
                        //gerando bytes a partir do path da imagem do usuário
                         users.setImage(ImageUtil.generateBytesImage(ImageUtil.createDialogAndGetPath()));
                    }
                    else {
                        throw new IllegalArgumentException();
                    }
                    //atualizando usuário
                    entityManager.merge(users);
                }
                entityManager.getTransaction().commit();

            Alerts.showAlert("Atualizado", null, "Imagem de perfil foi atualizada com sucesso", Alert.AlertType.CONFIRMATION);

        } catch (IllegalArgumentException | RollbackException error) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            //limpa o contexto de persistência para evitar estados inconsistentes
            if (entityManager.isOpen()) {
                entityManager.clear();
            }
            Alerts.showAlert("Erro", null, ("Falha ao atualizar a imagem da conta"), Alert.AlertType.ERROR);
        }
    }

    public static List<Message> findMessageById(Chanel id) {
        String jpql = "SELECT m FROM Message m WHERE m.id_chanel = :id";
        TypedQuery<Message> query = entityManager.createQuery(jpql, Message.class);
        query.setParameter("id", id);

        return query.getResultList();
    }

    public static String getTitleChanel(Long id) {
        Chanel chanel = entityManager.find(Chanel.class, id);

        if (chanel != null) {
            return chanel.getName();
        }

        return null;
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
