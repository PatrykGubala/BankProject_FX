package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.BankUser;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class BankCustomerController implements Initializable {
    private ViewFactory viewFactory;
    private BankUser loggedInUser;
    @FXML
    private Button accountsButton;

    @FXML
    private Button editButton;

    @FXML
    private Label emailLabel;

    @FXML
    private Button historyButton;

    @FXML
    private Button homePage;

    @FXML
    private Button logoutButton;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label nameLabel;
    @FXML
    void onAccountsButtonClicked(MouseEvent event) {

    }

    @FXML
    void onEditButtonClicked(MouseEvent event) {

    }

    @FXML
    void onHistoryButtonClicked(MouseEvent event) {

    }

    @FXML
    void onHomePageButtonClicked(MouseEvent event) {

    }

    @FXML
    void onLogoutButtonClicked(MouseEvent event) {

        Session session = Session.getInstance();
        BankUser user = session.getBankUser();
        System.out.println(user.getImie()+user.getNaziwsko());
        this.loggedInUser = null;
        viewFactory.showLoginWindow();
    }

    public void setUserInformation(BankUser bankUser){
        this.loggedInUser=bankUser;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            nameLabel.setText(loggedInUser.getImie()+" "+loggedInUser.getNaziwsko());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}

