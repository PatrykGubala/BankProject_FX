package com.example.bankproject_fx.controllers;
import com.example.bankproject_fx.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AppController implements Initializable {



    @FXML
    private BorderPane mainWindow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainWindow.setCenter(Session.getInstance().getViewFactory().getLoginWindow());

        Session.getInstance().getViewFactory().getCurrentWindowProperty().addListener((observableValue, oldVal, newVal)-> {
            switch (newVal){
                case LOGIN -> mainWindow.setCenter(Session.getInstance().getViewFactory().getLoginWindow());
                case REGISTER -> mainWindow.setCenter(Session.getInstance().getViewFactory().getRegisterWindow());
                case CUSTOMER -> mainWindow.setCenter(Session.getInstance().getViewFactory().getClientWindow());
            }
        });

    }

}
