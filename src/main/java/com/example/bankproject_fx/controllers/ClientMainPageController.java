package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.BankUser;
import com.example.bankproject_fx.model.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientMainPageController implements Initializable {

    @FXML
    private TextField accountAdressNumber;

    @FXML
    private TextField amountField;

    @FXML
    private ChoiceBox<String> currencyChoiceField;
    @FXML
    private ComboBox<String> cardChoiceComboBox;

    @FXML
    private Label emailLabel;

    @FXML
    private Label hiLabel;

    @FXML
    private TextArea messageField;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button sendMoneyButton;

    private BankUser bankUser;
    private ObservableList<String> cardNumbersAvailable = FXCollections.observableArrayList();
    private ObservableList<String> currenciesAvailable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cardNumbersAvailable.add("none");
        currenciesAvailable.add("EUR");
        currenciesAvailable.add("USD");
        currenciesAvailable.add("PLN");
        currenciesAvailable.add("YEN");
        bankUser = Session.getInstance().getBankUser();
        nameLabel.setText(bankUser.getImie()+bankUser.getNazwisko());
        emailLabel.setText(bankUser.getEmail());
        hiLabel.setText(bankUser.getImie());
        moneyLabel.setText(Double.toString(bankUser.getSaldo()));
        currencyChoiceField.setItems(currenciesAvailable);
        currencyChoiceField.getSelectionModel().selectFirst();
        cardChoiceComboBox.setItems(cardNumbersAvailable);
        cardChoiceComboBox.getSelectionModel().selectFirst();


    }

    @FXML
    void onChoiceCardComboBoxClicked(MouseEvent event) {

    }

    @FXML
    void onChoiceCurrencyComboBoxClicked(MouseEvent event) {

    }

    @FXML
    void onSendButtonClicked(MouseEvent event) {

    }
}
