package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.CurrentChoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class DashboardMenuController {

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
    void onAccountInformationButtonClicked(MouseEvent event) {

    }

    @FXML
    void onDashboardButtonClicked(MouseEvent event) {
        Session.getInstance().getViewFactory().getCurrentChoiceProperty().set(CurrentChoice.DASHBOARD);
    }

    @FXML
    void onHistoryButtonClicked(MouseEvent event) {
        //Session.getInstance().getViewFactory().changeSceneWindow();
        Session.getInstance().getViewFactory().getCurrentChoiceProperty().set(CurrentChoice.TRANSACTIONS);

    }

    @FXML
    void onLogoutButtonClicked(MouseEvent event) {
        Session.getInstance().setBankUser(null);
        Session.getInstance().getViewFactory().showLoginWindow();
    }

    @FXML
    void onYourAccountsButtonClicked(MouseEvent event) {

    }

}
