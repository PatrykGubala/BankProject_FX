module com.example.bankproject_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;


    opens com.example.bankproject_fx to javafx.fxml;
    opens com.example.bankproject_fx.model to javafx.fxml;
    opens com.example.bankproject_fx.controllers to javafx.fxml;
    opens com.example.bankproject_fx.Styles to javafx.fxml;
    opens com.example.bankproject_fx.dao to javafx.fxml;
    exports com.example.bankproject_fx;
    exports com.example.bankproject_fx.controllers;
    exports com.example.bankproject_fx.model;
    exports com.example.bankproject_fx.dao;

}
