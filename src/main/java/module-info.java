module org.code {
    // Importando recursos para o uso de interface gráfica com JavaFX
    requires javafx.fxml;
    requires javafx.controls;

    // Importando para org.code as APIs responsáveis pela persistência
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;

    // Entidades que serão mapeadas para as tabelas no banco de dados MySQL
    exports org.code.model.entities;
    opens org.code.model.entities;

    exports org.code.application to javafx.fxml;
    opens org.code.application;

    // Adicione estas linhas para expor o pacote de controladores
    opens org.code.gui.controllers to javafx.fxml;
}
