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
    private boolean orientation = true;

    @FXML
    private ImageView imageView;

    @FXML
    private VBox expandedMenu;


    @FXML
    private void toggleMenu() {
        FadeTransition ft = new FadeTransition(Duration.millis(300), expandedMenu);

        RotateTransition rotateTransition = new RotateTransition();

        rotateTransition.setNode(imageView);
        rotateTransition.setDuration(Duration.seconds(.3));
        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);

        if (orientation) {
            rotateTransition.setByAngle(90);
            orientation = !orientation;
        }
        else {
            rotateTransition.setByAngle(-90);
            orientation = !orientation;
        }


        rotateTransition.play(); // Iniciar a rotação

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
        rotateTransition.play();
    }
}
