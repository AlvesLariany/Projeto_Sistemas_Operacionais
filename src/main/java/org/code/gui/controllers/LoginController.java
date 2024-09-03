package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.code.gui.util.Alerts;
import org.code.model.util.HashUtil;
import org.code.gui.util.LoadViewInPane;
import org.code.model.util.TokenUserUtil;
import org.code.model.entities.Users;
import org.code.persistence.DataService;

public class LoginController {
    @FXML
    private TextField userEmail;

    @FXML
    private PasswordField userPassword;

    @FXML
    public void onButtonCreateClick() {
        LoadViewInPane.loadView("RegisterView.fxml", null, "Cadastre-se");
    }

    @FXML
    public void onLoginButtonClick() {
        Users user = DataService.findByHashEmail(HashUtil.defineHash(userEmail.getText()));

        if (user != null) {
            if (user.getPassword().equals(HashUtil.defineHash(userPassword.getText()))) {
                Alerts.showAlert("Sucesso", null, "Usuário cadastrado e senha conrreta, aproveite a plataforma", Alert.AlertType.CONFIRMATION);

                //salvando id do usuário em memória
                TokenUserUtil.setUserToken(user.getEmail());
                TokenUserUtil.setCurrentemail(userEmail.getText());

                //redirecionar para main posteriormente
                LoadViewInPane.loadView("MainView.fxml", null, "Main");
            }
            else {
                Alerts.showAlert("Erro", null, "A senha informada está incorreta", Alert.AlertType.WARNING);
            }
        }
        else {
            Alerts.showAlert("Erro", null, "Usuário não encontrado no sistema", Alert.AlertType.INFORMATION);
        }
    }
}
