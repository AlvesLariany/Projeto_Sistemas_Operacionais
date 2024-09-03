package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.code.gui.util.Alerts;
import org.code.gui.util.ImageUtil;
import org.code.gui.util.LoadViewInPane;
import org.code.model.entities.Users;
import org.code.model.util.TokenUserUtil;
import org.code.persistence.DataService;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private HBox rootElement;

    @FXML
    private ImageView icor_perfil;

    @FXML
    private Label user_name;

    @FXML
    private Label user_email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (rootElement.isVisible()) {

            Users users = DataService.findByHashEmail(TokenUserUtil.getUserToken());

            if (users != null) {
                icor_perfil.setImage(ImageUtil.getImageWithEmailUser(users.getEmail()));
                user_name.setText(users.getName());
                user_email.setText(TokenUserUtil.getCurrentemail());
            }
        }
    }

    private void updateImage() {
        Image image = ImageUtil.getImageWithEmailUser(TokenUserUtil.getUserToken());
        icor_perfil.setImage(image);
    }

    private void updateName() {
        Users users = DataService.findByHashEmail(TokenUserUtil.getUserToken());
        user_name.setText(users.getName());
    }

    @FXML
    public void onButtonChngeNameClicked() {
        String new_name = Alerts.showInputDialog();

        if (!(new_name.isEmpty())) {
            DataService.updateUserName(new_name);
            updateName();
        }
        else {
            Alerts.showAlert("Erro", null, "Falha ao atualizar o nome do usuário", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onUpdateImageClicked() {
        DataService.updateImageByHashEmail(TokenUserUtil.getUserToken());
        updateImage();
    }

    @FXML
    public void onSIngOutClicked() {
        LoadViewInPane.loadView("LoginView.fxml", null, "Login");
        Alerts.showAlert("Obrigado", null, "Obrigado por acessar a plataforma!", Alert.AlertType.CONFIRMATION);
    }
}
