package com.example.bankproject_fx.controllers;

import com.example.bankproject_fx.model.BankCard2;
import com.example.bankproject_fx.model.BankTransaction;
import com.example.bankproject_fx.model.BankUser2;
import com.example.bankproject_fx.model.Session;
import com.example.bankproject_fx.views.AlertHelper;
import com.example.bankproject_fx.views.TransactionsCellFactory;
import com.example.bankproject_fx.views.YourCardsRecordFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private ObservableList<BankCard2> cards = FXCollections.observableArrayList();
    List<String>cardNumbersAvailable = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        updateScene();
    }

    @FXML
    void onChoiceCardComboBoxClicked(MouseEvent event) {

    }

    @FXML
    void onSendButtonClicked(MouseEvent event) {
        double amountSent = 0.0;
        double amountReceived = 0.0;

        BankCard2 senderCard = Session.getInstance().getBankDatabase().getBankCard2ByBankCardNumber(cardChoiceComboBox.getValue());
        if (senderCard == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "No user selected.", "");
            return;
        }
        BankCard2 receiverCard = Session.getInstance().getBankDatabase().getBankCard2ByBankCardNumber(accountAdressNumber.getText().replaceAll("\\s", ""));
        if (receiverCard == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid receiver account number.", "");
            return;
        }
        try {
            amountSent = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid amount entered.", "");
            return;
        }
        try {
            amountReceived = Double.parseDouble(amountField.getText())*Session.getInstance().getBetterExchangeRates().get(receiverCard.getCurrency())/Session.getInstance().getBetterExchangeRates().get(senderCard.getCurrency());
        } catch (NumberFormatException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid amount entered.", "");
            return;
        }
        if (amountSent <= 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Amount must be greater than 0.", "");
            return;
        }

        if (senderCard.getBalance() < amountSent) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Insufficient funds.", "");
            return;
        }
        receiverCard.setBalance(receiverCard.getBalance()+amountReceived);
        senderCard.setBalance(senderCard.getBalance()-amountSent);
        Session.getInstance().getBankDatabase().updateBankCard2Balance(receiverCard);
        Session.getInstance().getBankDatabase().updateBankCard2Balance(senderCard);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        BankTransaction transaction = new BankTransaction(senderCard.getCustomer_id(), receiverCard.getCustomer_id(),formattedDateTime,messageField.getText(),amountSent/Session.getInstance().getBetterExchangeRates().get(senderCard.getCurrency()));
        Session.getInstance().getBankDatabase().addBankTransaction(transaction);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Success", "Money sent successfully.", "");

        updateScene();



    }
    public void updateScene(){

        cards.clear();
        Session.getInstance().setBankUser2(Session.getInstance().getBankDatabase().getBankUserByCustomerId2(Session.getInstance().getBankUser2().getCustomer_id()));
        cards.setAll( Session.getInstance().getBankUser2().getBankCards());

        cardsListView.setCellFactory(new YourCardsRecordFactory());
        cardsListView.setItems(cards);

        ObservableList<String> list = FXCollections.observableArrayList();
        for (BankCard2 card : cards) {
            list.add(card.getBank_card_number());
        }
        cardChoiceComboBox.setItems(list);
    }

}
