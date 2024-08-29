package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/LoginView.fxml"));
        Pane pane = fxmlLoader.load();

        scene = new Scene(pane);

        Pane rootPane = (Pane) scene.getRoot();

        scene.getStylesheets().add(getClass().getResource("/gui/styles/loginStyle.css").toExternalForm());

        Pane paneMain = (Pane) scene.getRoot();

        System.out.println("Filhos do scene exibido do main -> " + rootPane.getChildren());

        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getSceneMainReference() {
        return scene;
    }
}