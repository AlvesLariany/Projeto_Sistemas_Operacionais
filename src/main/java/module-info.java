module org.code {
    requires javafx.fxml;
    requires javafx.controls;

    exports org.code.gui.controllers to javafx.fxml;
    opens org.code.gui.controllers to javafx.fxml;

    exports org.code.application to javafx.fxml;
    opens org.code.application;
}