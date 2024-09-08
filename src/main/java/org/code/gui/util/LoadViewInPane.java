package org.code.gui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.code.application.App;

import java.io.IOException;

public class LoadViewInPane {

    public LoadViewInPane() {}

    public static void loadView(String viewName, String cssFileName, String titlePane) {
        // Pegando a cena principal, sempre do main
        Stage mainStage = App.getStageMainReference();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoadViewInPane.class.getResource("/gui/views/" + viewName));
            Pane pane = fxmlLoader.load();

            Scene sceneNew = new Scene(pane);

            if (cssFileName != null) {
                try {
                    pane.getStylesheets().add(LoadViewInPane.class.getResource("/gui/styles/" + cssFileName).toExternalForm());
                } catch (NullPointerException err) {
                    System.out.println("Css load have failed: " + err.getMessage());
                }
            }

            fullScreen(mainStage);

            //sobrecrevendo a tela atual com o que foi passada
            mainStage.setTitle(titlePane);
            mainStage.setScene(sceneNew);
            mainStage.show();
        } catch (IOException error) {
            System.out.println("Error to load view: " + error.getMessage());
        }
    }

    public static void fullScreen(Stage stage) {
        Screen screen = Screen.getPrimary();
        double width = screen.getBounds().getWidth();
        double height = screen.getBounds().getHeight();

        stage.setWidth(width);
        stage.setHeight(height);
    }
}
