package com.example.bankproject_fx.views;

import com.example.bankproject_fx.HelloApplication;
import com.example.bankproject_fx.controllers.TransactionRecordController;
import com.example.bankproject_fx.controllers.YourCardsRecordController;
import com.example.bankproject_fx.model.BankCard2;
import com.example.bankproject_fx.model.BankTransaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


import java.io.IOException;

//kod wzięty z https://www.baeldung.com/javafx-listview-display-custom-items
public class YourCardsRecordFactory implements Callback<ListView<BankCard2>, ListCell<BankCard2>> {
    @Override
    public ListCell<BankCard2> call(ListView<BankCard2> param) {
        return new ListCell<BankCard2>(){
            @Override
            public void updateItem(BankCard2 bankCard2, boolean empty) {
                super.updateItem(bankCard2, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (bankCard2 != null) {

                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("YourCardsRecord.fxml"));
                    YourCardsRecordController yourCardsRecordController = new YourCardsRecordController(bankCard2);
                    fxmlLoader.setController(yourCardsRecordController);
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