package org.code.gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class Alerts {
    public static void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.show();
    }

    public static String showInputDialog() {
        String inputText = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alterar");
        alert.setHeaderText("Digite o novo nome para seu perfil: ");

        // Cria um campo de texto
        TextField textField = new TextField();
        textField.setPromptText("Digite aqui");

        // Adiciona o campo de texto ao diálogo
        VBox vbox = new VBox(textField);
        vbox.setSpacing(10);
        alert.getDialogPane().setContent(vbox);

        // Mostra o diálogo e aguarda a resposta
        Optional<ButtonType> result = alert.showAndWait();

        // Processa a resposta e o texto digitado
        if (result.isPresent() && result.get() == ButtonType.OK) {
            inputText = textField.getText();
        }
        return inputText;
    }
}
