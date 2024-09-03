package org.code.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.code.application.App;
import org.code.model.entities.Users;
import org.code.persistence.DataService;

import java.io.*;

public class ImageUtil {
    private static final String DEFAULT_NAME = "icon_perfil_default_6212.png";
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

    public static Image getImageWithEmailUser(String userEmail) {
        Users users = DataService.findByHashEmail(userEmail);

        ByteArrayInputStream bais = new ByteArrayInputStream(users.getImage());
        Image image = new Image(bais);

        return image;
    }

    public static byte[] generateBytesFromRelative(String resourcePath) {

        byte[] bytesImage = null;
        try (InputStream inputStream = ImageUtil.class.getResourceAsStream("/media/" + resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Imagem não encontrada: " + resourcePath);
            }
            bytesImage = inputStream.readAllBytes();
        } catch (IOException error) {
            System.out.println("Erro ao converter a imagem em bytes: " + error.getMessage());
        }
        return bytesImage;
    }

    private static boolean verifyIsRelativeOrAbsolute(String path) {
        String indexStart = "/media/";

        String relativePath = path.substring(indexStart.length());

        System.out.println("SUBSTRING = " + relativePath);

        return relativePath.equals(DEFAULT_NAME);
    }

    public static byte[] generateBytesImage(String path) {
        byte[] bytesImage = null;

        if (verifyIsRelativeOrAbsolute(path)) {
            bytesImage = generateBytesFromRelative(DEFAULT_NAME);
        }
        else {
            File file = new File(path);

            try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
                bytesImage = new byte[(int) file.length()];

                fileInputStream.read(bytesImage);
            } catch (IOException error) {
                System.out.println("Erro ao converter a imagem em bytes: " + error.getMessage());
            }
        }

        return bytesImage;
    }


}
