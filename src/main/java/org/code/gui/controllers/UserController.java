package org.code.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.code.gui.util.Alerts;
import org.code.gui.util.ImageUtil;
import org.code.gui.util.LoadViewInPane;
import org.code.model.util.TokenUserUtil;
import org.code.persistence.DataService;

public class UserController {

    @FXML
    private ImageView imageView;

    @FXML
    public void onUpdateImageButtonClicked() {
        DataService.updateImageByHashEmail(TokenUserUtil.getUserToken());

        Image image = DataService.getImageByHashEmail(TokenUserUtil.getUserToken());

        if (image != null) {
            imageView.setImage(image);
        }
        else {
            Alerts.showAlert("Erro", null, "Falha ao carregar imagem do perfil, tente novamente", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if(imageView.isVisible()) {
                imageView.setImage(ImageUtil.getImageWithUserToken(TokenUserUtil.getUserToken()));
            }
        });
    }

    @FXML
    public void onBackLoginClick() {
        Alerts.showAlert("Obrigado", null, "Obrigado por acessar a plataforma, esperamos ter ajudado", Alert.AlertType.CONFIRMATION);
        LoadViewInPane.loadView("LoginView.fxml", null, "Login");
    }

}
