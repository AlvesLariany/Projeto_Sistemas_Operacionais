package org.code.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.code.gui.util.LoadViewInPane;

public class App extends Application {
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/LoginView.fxml"));
        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);

        Pane mainPane = (Pane) scene.getRoot();

        LoadViewInPane.fullScreen(stage);

        mainStage.setTitle("Login");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStageMainReference() {
        return mainStage;
    }
}