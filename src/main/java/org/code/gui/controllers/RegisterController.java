package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.code.gui.util.Alerts;
import org.code.gui.util.LoadViewInPane;
import org.code.model.entities.User;
import org.code.persistence.JpaServices;


public class RegisterController {
    //classe personalizada com servicos relacionados ao banco
    private final JpaServices jpaServices = new JpaServices();

    //classe usada para manipular alteração de telas
    LoadViewInPane loadViewInPane = new LoadViewInPane();

    @FXML
    private TextField areaName;

    @FXML
    private TextField areaEmail;

    @FXML
    private PasswordField areaPassword;

    @FXML
    private Button registerButton;

    private User createNewUser(String name, String email, String password) {
        return new User (name, email, password);
    }

    @FXML
    public void onActionButtonCreateAccount() {
        User user = createNewUser(areaName.getText(), areaEmail.getText(), areaPassword.getText());

        if (jpaServices.saveItem(user)) {
            String successMessage = "Usuário " + areaName.getText() + " criado com sucesso";

            Alerts.showAlert("Usuário criado", null, successMessage, Alert.AlertType.CONFIRMATION);

        }
        else {
            Alerts.showAlert("Falha ao criar usuário", null, "Erro ao criar o usuário, verifique os campos e tente novamente", Alert.AlertType.ERROR);
        }
        loadViewInPane.loadView("LoginView.fxml", "loginStyle.css", "Login");
    }
}