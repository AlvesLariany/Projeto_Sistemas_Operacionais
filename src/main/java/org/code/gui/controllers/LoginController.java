package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.code.gui.util.Alerts;

public class LoginController {
    @FXML
    private VBox titleSide;

    @FXML
    private HBox loginSide;

    @FXML
    private Pane loginInputArea;

    @FXML
    private Button loginButton;

    //evento de clique do botão
    @FXML
    public void onButtonLoginAction() {
        Alerts.showAlert("Inválido", null, "Usuário não cadastrado", Alert.AlertType.ERROR);
    }

}
