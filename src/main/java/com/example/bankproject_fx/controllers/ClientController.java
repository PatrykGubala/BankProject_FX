package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private  BorderPane borderPane;

    public void changeScene(Scene centerScene){
        borderPane.setCenter(centerScene.getRoot());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Session.getInstance().getViewFactory().getCurrentChoiceProperty().addListener((observableValue, oldVal, newVal)-> {
            switch (newVal){
                case TRANSACTIONS -> borderPane.setCenter(Session.getInstance().getViewFactory().getTransactionsView());
                case DASHBOARD -> borderPane.setCenter(Session.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
