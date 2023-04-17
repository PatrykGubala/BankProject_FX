package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.BankCard2;
import com.example.bankproject_fx.model.BankTransaction;
import com.example.bankproject_fx.model.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class YourCardsRecordController implements Initializable {

    @FXML
    private Label cardCurrencyId;

    @FXML
    private Label cardNumberId;

    @FXML
    private Label cardOwnerId;

    @FXML
    private Label validThruId;

    private BankCard2 bankCard2;
    public YourCardsRecordController(BankCard2 bankCard2){
        this.bankCard2 = bankCard2;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardCurrencyId.setText(bankCard2.getCurrency());
        cardNumberId.setText(bankCard2.getBank_card_number());
        cardOwnerId.setText(Session.getInstance().getBankUser2().getFirst_name()+Session.getInstance().getBankUser2().getLast_name());
        validThruId.setText(bankCard2.getExpiryDate());

    }

}
