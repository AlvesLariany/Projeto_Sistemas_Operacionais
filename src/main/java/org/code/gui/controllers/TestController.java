package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/*  Testando os controladores das interfaces gráficas  */

public class TestController {
    @FXML
    private Button buttonTest;

    @FXML
    private Label outputClick;

    @FXML
    public void onButtonAction() {
        outputClick.setText("O botão foi clicado!");
    }
}
