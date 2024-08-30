package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.code.gui.util.LoadViewInPane;

public class LoginController {
    @FXML
    private Button buttonCreateAccount;

    @FXML
    public void onButtonCreateClick() {
        LoadViewInPane.loadView("RegisterView.fxml", null, "Cadastre-se");
    }
}
