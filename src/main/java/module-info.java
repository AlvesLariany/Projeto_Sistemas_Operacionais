module org.code {
    requires javafx.fxml;
    requires javafx.controls;

    //expondo controladores
    exports org.code.gui.controllers to javafx.fxml;
    opens org.code.gui.controllers to javafx.fxml;

    //expondo diretorio principal
    exports org.code.application to javafx.fxml;
    opens org.code.application;
}