package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.code.gui.util.Alerts;
import org.code.gui.util.TokenUserUtil;
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

}
