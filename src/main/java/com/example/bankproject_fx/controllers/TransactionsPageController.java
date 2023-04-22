package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.BankTransaction;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.TransactionsCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionsPageController implements Initializable {
    @FXML
    private ListView<BankTransaction> listView ;
    @FXML
    private ChoiceBox<String> sortingComboBoxId;


    private ObservableList<String> transactions = FXCollections.observableArrayList();
    @FXML
    private Button refreshButton;

    @FXML
    void onRefreshButtonClicked(MouseEvent event) {
        updateTransactions();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> sortingList = new ArrayList<>(Arrays.asList(
                "Data rosnąco",
                "Data malejąco",
                "Numer konta OD malejąco",
                "Numer konta OD rosnąco",
                "Numer konta DO malejąco",
                "Numer konta DO rosnąco"));
        sortingComboBoxId.setItems(FXCollections.observableArrayList(sortingList));
        sortingComboBoxId.setOnAction(event -> {
            String selectedItem = sortingComboBoxId.getSelectionModel().getSelectedItem();
            List<BankTransaction> transactionList = new ArrayList<>();

            try{
                transactionList = Session.getInstance().getBankDatabase().getBankTransactionsByCustomerId(Session.getInstance().getBankUser2().getCustomer_id());
            }catch(Exception e){
                return;
            }
            if(selectedItem.equals("Data rosnąco")){
                transactionList.sort(Comparator.comparing(BankTransaction::getDate));
            } else if (selectedItem.equals("Data malejąco")) {
                transactionList.sort(Comparator.comparing(BankTransaction::getDate).reversed());
            } else if (selectedItem.equals("Numer konta OD malejąco")) {
                transactionList.sort(Comparator.comparing(BankTransaction::getFrom).reversed());
            } else if (selectedItem.equals("Numer konta OD rosnąco")) {
                transactionList.sort(Comparator.comparing(BankTransaction::getFrom));
            } else if (selectedItem.equals("Numer konta DO malejąco")) {
                transactionList.sort(Comparator.comparing(BankTransaction::getTo).reversed());
            } else if (selectedItem.equals("Numer konta DO rosnąco")) {
                transactionList.sort(Comparator.comparing(BankTransaction::getTo));
            }
            listView.getItems().clear();
            listView.setCellFactory(new TransactionsCellFactory());
            listView.getItems().addAll(transactionList);
        });
        //listView.getItems().addAll(items2);

        updateTransactions();

// Add the transactions to the list view
       // transactionListView.getItems().addAll(transactionList);

    }

    public void updateTransactions() {
        try{
            List<BankTransaction> transactionList = new ArrayList<>();
            transactionList = Session.getInstance().getBankDatabase().getBankTransactionsByCustomerId(Session.getInstance().getBankUser2().getCustomer_id());
            listView.getItems().clear();
            listView.setCellFactory(new TransactionsCellFactory());
            listView.getItems().addAll(transactionList);
        }catch(Exception e){
            return;
        }

    }
    private void populateTransactions() {
        // Code to read transaction data from a file or database and add it to the transactions list
        //transactions.add(new BankTransaction("2022-03-25", "Groceries", "212","123",100.20));

    }
}
