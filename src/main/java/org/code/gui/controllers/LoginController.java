package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.code.application.App;
import org.code.gui.util.Alerts;
import org.code.gui.util.LoadViewInPane;

public class LoginController {
    LoadViewInPane loadViewInPane = new LoadViewInPane();

    @FXML
    private VBox titleSide;

    @FXML
    private HBox loginSide;

    @FXML
    private Pane loginInputArea;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;

    @FXML
    public void onCreateAccountButtonOnAction() {
        loadViewInPane.loadView("RegisterView.fxml", "RegisterStyle.css", "Register");
    }

}
