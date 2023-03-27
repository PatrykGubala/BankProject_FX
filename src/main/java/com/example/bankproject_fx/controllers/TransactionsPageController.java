package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.BankTransaction;
import com.example.bankproject_fx.views.TransactionsCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionsPageController implements Initializable {
    @FXML
    private ListView<BankTransaction> listView ;
    String [] items2 = {"123", "213", "321"};

    private ObservableList<String> transactions = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> items =FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");

        //listView.getItems().addAll(items2);

        List<BankTransaction> transactionList = new ArrayList<>();
        transactionList.add(new BankTransaction("2022-01-01", "Salary", "disua","2fdsa",5000.00));
        transactionList.add(new BankTransaction("2022bvcx1-01", "Safsady", "dfgasua","2fbvxcsa",50030.00));

        listView.setCellFactory(new TransactionsCellFactory());

        listView.getItems().addAll(transactionList);

// Add the transactions to the list view
       // transactionListView.getItems().addAll(transactionList);

    }
    private void populateTransactions() {
        // Code to read transaction data from a file or database and add it to the transactions list
        //transactions.add(new BankTransaction("2022-03-25", "Groceries", "212","123",100.20));

    }
}
