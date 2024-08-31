package org.code.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.code.application.App;

import java.io.File;

public class ImageUtil {
    public static String createDialogAndGetPath() {
        FileChooser fileChooser = new FileChooser();

        //filtrando apenas imagens nos arquivos a serem exibidos
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        Stage stage = App.getStageMainReference();

        //exibindo pane com os arquivos para ser escolhido
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            return file.getAbsolutePath();
        }
        else {
            Alerts.showAlert("Erro", null, "Erro ao selecionar o arquivo, tente novamente", Alert.AlertType.ERROR);
        }
        return " ";
    }

    public static Image createFileWithPath(String pathToFile) {
        File file = new File(pathToFile);

        if (!file.exists()) {
            Alerts.showAlert("Erro", null, "Não foi possível carregar a imagem, tente novamente", Alert.AlertType.ERROR);
        }

        return new Image(file.toURI().toString());
    }
}
