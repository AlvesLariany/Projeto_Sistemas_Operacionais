module org.code {
    //importando recurso para o suo de interface gráfica com javafx
    requires javafx.fxml;
    requires javafx.controls;

    //importando para org.code as api's responsáveis pela persistência
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;

    //entidades que serão mapeadas para as tabelas no banco de dados mysql
    exports org.code.model.entities;
    opens org.code.model.entities;

    //expondo controladores do javafx
    exports org.code.gui.controllers to javafx.fxml;
    opens org.code.gui.controllers to javafx.fxml;

    //expondo diretorio principal para o javafx
    exports org.code.application to javafx.fxml;
    opens org.code.application;
}