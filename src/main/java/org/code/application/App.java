package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/RegisterView.fxml"));
        VBox vbox = fxmlLoader.load();

        Scene scene = new Scene(vbox);

        scene.getStylesheets().add(getClass().getResource("/gui/styles/RegisterStyle.css").toExternalForm());

        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
