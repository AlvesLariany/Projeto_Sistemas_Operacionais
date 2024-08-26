package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/* Teste de exibição de interface */

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/LoginView.fxml"));
        HBox hBox = fxmlLoader.load();

        Scene scene = new Scene(hBox);

        scene.getStylesheets().add(getClass().getResource("/gui/styles/loginStyle.css").toExternalForm());

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args ) {
        launch();
    }
}
