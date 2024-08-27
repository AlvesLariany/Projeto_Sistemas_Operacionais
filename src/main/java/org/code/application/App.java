package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* Teste de exibição de interface */

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/MenuView.fxml"));
        VBox vBox = fxmlLoader.load();

        Scene scene = new Scene(vBox);

        //injetando styles no componente
        scene.getStylesheets().add(getClass().getResource("/gui/styles/menuStyle.css").toExternalForm());

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args ) {
        launch();
    }
}
