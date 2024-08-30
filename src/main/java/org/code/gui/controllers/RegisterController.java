package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.code.gui.util.Alerts;
import org.code.gui.util.HashUtil;
import org.code.gui.util.LoadViewInPane;
import org.w3c.dom.Text;

public class RegisterController {
    private final int TAM_INFO_USER = 3;
    private final int USER_NAME = 0;
    private final int USER_EMAIL = 1;
    private final int USER_PASSWORD = 2;
    @FXML
    private TextField nameUser;
    @FXML
    private TextField emailUser;
    @FXML
    private PasswordField passwordUser;

    private String[] getInformationUser() {
        String[] informationUser = new String[TAM_INFO_USER];
        informationUser[USER_NAME] = nameUser.getText();
        informationUser[USER_EMAIL] = emailUser.getText();
        informationUser[USER_PASSWORD] = passwordUser.getText();

        return informationUser;
    }

    public boolean isHaveNull(String[] information) {
        for (String info : information) {
            if (info.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @FXML
    public void onButtonCancelClicked() {
        LoadViewInPane.loadView("LoginView.fxml", null, "Login");

        Alerts.showAlert("Criação interrompida", null, "A criação da conta foi cancelada pelo usuário", Alert.AlertType.INFORMATION);
    }

    @FXML
    public void onButtonCreateClicked() {
        String[] informationUser = new String[TAM_INFO_USER];
        informationUser = getInformationUser();

        if (isHaveNull(informationUser)) {
            //metodo pra salvar no banco de dados
            //quando criar o usuário, verificar no banco se o email já foi registrado

            //mandar senha e email criptografados para o banco de dados, realizar comparação na consulta

            //criar termos de uso da plataforma
            System.out.println("Senha criptografada: " + HashUtil.defineHash(informationUser[USER_PASSWORD]));

            Alerts.showAlert("Conta criada", null, "Conta criada com sucesso, realize o login para aproveitar a plataforma", Alert.AlertType.CONFIRMATION);
        }
        else {
            Alerts.showAlert("Falha", null, "Error ao criar conta, não é permitido que os campos (nome, email ou senha) fiquem vazios, tente novamente", Alert.AlertType.ERROR);
        }
        LoadViewInPane.loadView("LoginView.fxml", null, "Login");
    }
}
