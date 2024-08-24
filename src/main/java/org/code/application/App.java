package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/* Teste de exibição de interface */

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/TestView.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        Scene sceneTest = new Scene(anchorPane);

        stage.setTitle("teste");
        stage.setScene(sceneTest);
        stage.show();
    }

    public static void main( String[] args ) {
        launch();
    }
}
