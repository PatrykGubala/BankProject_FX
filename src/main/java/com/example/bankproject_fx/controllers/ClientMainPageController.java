package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.BankUser;
import com.example.bankproject_fx.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMainPageController implements Initializable {

    @FXML
    private TextField amount_fld;

    @FXML
    private Label emailLabel;

    @FXML
    private Label hiLabel;

    @FXML
    private TextArea message_fld;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField payee_fld;

    @FXML
    private Button sent_money_btn;

    private BankUser bankUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bankUser = Session.getInstance().getBankUser();
        nameLabel.setText(bankUser.getImie()+bankUser.getNazwisko());
        emailLabel.setText(bankUser.getEmail());
        hiLabel.setText(bankUser.getImie());
        moneyLabel.setText(Double.toString(bankUser.getSaldo()));

    }
}
