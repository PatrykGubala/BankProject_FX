package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.CurrentChoice;
import com.example.bankproject_fx.views.CurrentWindow;
import com.example.bankproject_fx.views.Mode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardMenuController implements Initializable {

    @FXML
    private Button accountInformationButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button yourAccountsButton;


    @FXML
    private AnchorPane mainWindow;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onChangeModeButtonClicked(MouseEvent event) {
        Session.getInstance().getViewFactory().changeMode();
    }

    @FXML
    void onAccountInformationButtonClicked(MouseEvent event) {

    }

    @FXML
    void onDashboardButtonClicked(MouseEvent event) {
        Session.getInstance().getViewFactory().getCurrentChoiceProperty().set(CurrentChoice.DASHBOARD);
    }

    @FXML
    void onHistoryButtonClicked(MouseEvent event) {
        Session.getInstance().getViewFactory().getCurrentChoiceProperty().set(CurrentChoice.TRANSACTIONS);

    }

    @FXML
    void onLogoutButtonClicked(MouseEvent event) {
        Session.getInstance().setBankUser2(null);
        Session.getInstance().getViewFactory().getCurrentWindowProperty().set(CurrentWindow.LOGIN);
    }

    @FXML
    void onYourAccountsButtonClicked(MouseEvent event) {
        Session.getInstance().getViewFactory().getCurrentChoiceProperty().set(CurrentChoice.YOUR_CARDS);
    }

}
