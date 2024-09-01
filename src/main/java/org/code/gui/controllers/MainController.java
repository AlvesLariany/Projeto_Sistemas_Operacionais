package org.code.gui.controllers;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private ImageView imageView;

    @FXML
    private VBox expandedMenu;




    @FXML
    private void toggleMenu() {
        FadeTransition ft = new FadeTransition(Duration.millis(300), expandedMenu);
        /*RotateTransition rotate = new RotateTransition(Duration.millis(200), imageView);*/
        /*Image image = new Image(getClass().getResource("/resources/media/seta.png").toExternalForm());*/
        /*imageView.setImage(image);
        imageView.setPreserveRatio(true);*/
        if (expandedMenu.isVisible()) {
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setOnFinished(e -> expandedMenu.setVisible(false));
        } else {
            expandedMenu.setVisible(true);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);

        }

        ft.play();
        /*rotate.play();*/

    }
}
