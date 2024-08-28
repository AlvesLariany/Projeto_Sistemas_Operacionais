package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/ChatView.fxml"));
        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);

        scene.getStylesheets().add(getClass().getResource("/gui/styles/ChatStyle.css").toExternalForm());

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
