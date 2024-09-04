package org.code.gui.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.code.application.App;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageControl implements Initializable {
    private static final String URL_CONTENT = "https";
    @FXML
    private ImageView user_icon;
    @FXML
    private Label user_name;
    @FXML
    private Label date_send;
    @FXML
    private Label hour_send;
    @FXML
    private Text message_content;
    @FXML
    private VBox rootMessage;
    @FXML
    private Region backgroundRegion;
    @FXML
    private StackPane stackPaneMessage;

    private boolean checkHaveLink (String text) {
        return text.toLowerCase().contains(URL_CONTENT);
    }

    private String getUrlInText(String text) {
        int indexStart = text.toLowerCase().indexOf(URL_CONTENT);
        String urlStart = text.substring(indexStart);

        int indexFinal = urlStart.indexOf(" ");

        System.out.println(urlStart);
        System.out.println("-1 NÃO ATIVO " + indexFinal);

        if (indexFinal == -1) {
            System.out.println("-1 ativo: " + urlStart.length());
            indexFinal = urlStart.length();
        }

        return text.substring(indexStart, indexFinal);
    }

    private void onTextWithUrlClicked(Event event, String text) {
        String url = getUrlInText(text);

        App.getHostSerciveInApp().showDocument(url);
    }

    public void setContent(String name, String date, String hour, String text, Image icon) {
        if (icon != null){
            user_icon.setImage(icon);
            Rectangle rectangle = new Rectangle(0, 0, 38, 38);
            rectangle.setArcHeight(35);
            rectangle.setArcWidth(35);
            user_icon.setClip(rectangle);
        }
        user_name.setText(name);
        date_send.setText(date);
        hour_send.setText(hour);
        if (checkHaveLink(text)) {
            message_content.setCursor(Cursor.HAND);
            message_content.setOnMouseClicked(event -> onTextWithUrlClicked(event, text));
            message_content.setText(text);
        }
        else {
            message_content.setText(text);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (backgroundRegion.isVisible()) {
            backgroundRegion.prefWidthProperty().bind(stackPaneMessage.widthProperty());
            backgroundRegion.prefWidthProperty().bind(stackPaneMessage.heightProperty());
        }
    }
}
