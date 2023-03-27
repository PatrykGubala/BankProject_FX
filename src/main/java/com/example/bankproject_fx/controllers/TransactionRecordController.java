package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.BankTransaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionRecordController implements Initializable {

    @FXML
    private Label amountLabel;

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    @FXML
    private Label transactionDateLabel;


    private final BankTransaction transaction;

    public TransactionRecordController(BankTransaction transaction){
        this.transaction = transaction;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromLabel.setText(transaction.getFrom());
        toLabel.setText(transaction.getTo());
        transactionDateLabel.setText(transaction.getDate());
        amountLabel.setText(Double.toString(transaction.getAmount()));

    }
}
