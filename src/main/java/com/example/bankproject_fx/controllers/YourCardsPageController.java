package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.BankCard2;
import com.example.bankproject_fx.model.BankTransaction;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.TransactionsCellFactory;
import com.example.bankproject_fx.views.YourCardsRecordFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class YourCardsPageController implements Initializable {

    @FXML
    private TextField accountAdressNumber;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> cardChoiceComboBox;

    @FXML
    private ListView<BankCard2> cardsListView;

    @FXML
    private Label currencyId;

    @FXML
    private TextArea messageField;

    @FXML
    private Button sendMoneyButton;

    String [] items2 = {"123", "213", "321"};

    private ObservableList<String> cards = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        //listView.getItems().addAll(items2);

        cardsListView.setCellFactory(new YourCardsRecordFactory());

        cardsListView.getItems().addAll(Session.getInstance().getBankUser2().getBankCards());
    }

    @FXML
    void onChoiceCardComboBoxClicked(MouseEvent event) {

    }

    @FXML
    void onSendButtonClicked(MouseEvent event) {

    }

}
