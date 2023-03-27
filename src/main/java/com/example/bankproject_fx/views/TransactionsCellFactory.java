package com.example.bankproject_fx.views;

import com.example.bankproject_fx.HelloApplication;
import com.example.bankproject_fx.controllers.TransactionRecordController;
import com.example.bankproject_fx.model.BankTransaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


import java.io.IOException;

//kod wzięty z https://www.baeldung.com/javafx-listview-display-custom-items
public class TransactionsCellFactory implements Callback<ListView<BankTransaction>, ListCell<BankTransaction>> {
    @Override
    public ListCell<BankTransaction> call(ListView<BankTransaction> param) {
        return new ListCell<BankTransaction>(){
            @Override
            public void updateItem(BankTransaction bankTransaction, boolean empty) {
                super.updateItem(bankTransaction, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (bankTransaction != null) {

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TransactionRecord.fxml"));
                    TransactionRecordController transactionRecordController = new TransactionRecordController(bankTransaction);
                    fxmlLoader.setController(transactionRecordController);
                    setText(null);
                    try {
                        setGraphic(fxmlLoader.load());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
        };
    }
}