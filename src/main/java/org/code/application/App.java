package org.code.application;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.code.gui.util.LoadViewInPane;
import org.code.persistence.Settlement;

public class App extends Application {
    private static Stage mainStage;

    private static HostServices hostServiceMain = null;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        //serviço que redireciona para o browser quando um edital é anexado na mensagem
        hostServiceMain = getHostServices();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/views/LoginView.fxml"));
        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);

        Pane mainPane = (Pane) scene.getRoot();

        LoadViewInPane.fullScreen(stage);

        Settlement.implement();

        mainStage.setTitle("Login");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //forma de "pegar a tela" e outras classes
    public static Stage getStageMainReference() {
        return mainStage;
    }

    //serviço para abrir o browser
    public static HostServices getHostSerciveInApp() {
        return hostServiceMain;
    }
}