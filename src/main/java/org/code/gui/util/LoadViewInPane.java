package org.code.gui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.code.application.App;

import java.io.IOException;

public class LoadViewInPane {

    public LoadViewInPane() {}

    public void loadView(String viewName, String cssFileName, String titlePane) {
        //pegando cena principal, sempre do main
        Scene mainScene = App.getSceneMainReference();
        Stage mainStage = (Stage) mainScene.getWindow();

        //limpando o conteúdo da cena atual
        Pane rootPane = (Pane) mainScene.getRoot();
        rootPane.getChildren().clear();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/" + viewName));
            Pane pane = fxmlLoader.load();

            //adicionando a view carregada
            rootPane.getChildren().addAll(pane);

            //limpando o css antigo
            mainScene.getStylesheets().clear();

            if (cssFileName != null) {
                try {
                    mainScene.getStylesheets().add(getClass().getResource("/gui/styles/" + cssFileName).toExternalForm());
                } catch (NullPointerException err) {
                    System.out.println("Css load have failed: " + err.getMessage());
                }
            }

            mainStage.setTitle(titlePane);
            mainStage.show();
        } catch (IOException error) {
            System.out.println("Error to load view: " + error.getMessage());
        }
    }
}
