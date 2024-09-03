package org.code.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MessageControl {
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
    public void initialize() {
        // Inicialize qualquer coisa se necessário
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
        message_content.setText(text);
    }
}
