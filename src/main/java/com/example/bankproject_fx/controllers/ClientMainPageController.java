package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.*;
import com.example.bankproject_fx.views.AlertHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientMainPageController implements Initializable {
    @FXML
    private Button addCardButton;
    @FXML
    private TextField amountAddCardField;
    @FXML
    private TextField accountAdressNumber;

    @FXML
    private TextField amountField;

    @FXML
    private ChoiceBox<String> currencyChoiceField;
    @FXML
    private ComboBox<String> cardChoiceComboBox;
    @FXML
    private ChoiceBox<String> currencyChoiceAddCardField;

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
    private ObservableList<String> currenciesAll = FXCollections.observableArrayList();
    private Validator validator = new Validator();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set up the text formatter to allow only numbers
        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), null,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("\\d*")) {
                        return change;
                    } else {
                        return null;
                    }
                });
        amountField.setTextFormatter(formatter);

        //16 digit with space bar as separation
        accountAdressNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Remove any non-digit characters from the text
                newValue = newValue.replaceAll("\\D", "");

                // Insert space after every group of four digits
                if (newValue.length() > 4) {
                    newValue = newValue.substring(0, 4) + " " + newValue.substring(4);
                }
                if (newValue.length() > 9) {
                    newValue = newValue.substring(0, 9) + " " + newValue.substring(9);
                }
                if (newValue.length() > 14) {
                    newValue = newValue.substring(0, 14) + " " + newValue.substring(14);
                }
                if (newValue.length() > 19) {
                    newValue = newValue.substring(0, 19);
                }

                // Update the text in the TextField
                accountAdressNumber.setText(newValue);
            }
        });

        validatorCheck(amountField);
        cardNumbersAvailable.add("none");
        currenciesAvailable.add("EUR");
        currenciesAvailable.add("USD");
        currenciesAvailable.add("PLN");
        currenciesAvailable.add("YEN");
       // bankUser = Session.getInstance().getBankUser();
        //nameLabel.setText(bankUser.getImie()+bankUser.getNazwisko());
        //emailLabel.setText(bankUser.getEmail());
        //hiLabel.setText(bankUser.getImie());
        //moneyLabel.setText(Double.toString(bankUser.getSaldo()));
        currencyChoiceField.setItems(currenciesAvailable);
        currencyChoiceField.getSelectionModel().selectFirst();
        cardChoiceComboBox.setItems(cardNumbersAvailable);
        cardChoiceComboBox.getSelectionModel().selectFirst();

        updateClientScene();

        currenciesAll.add("EUR");
        currenciesAll.add("USD");
        currenciesAll.add("PLN");
        currenciesAll.add("YEN");
        currencyChoiceAddCardField.setItems(currenciesAll);
        currencyChoiceAddCardField.getSelectionModel().selectFirst();



    }
    @FXML
    void onAddCardButtonClicked(MouseEvent event) {


        String currencyChoosen= currencyChoiceAddCardField.getValue();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        String expiryDate = now.format(formatter);
        //double balance = Double.parseDouble(amountAddCardField.toString());
        String customerId = Session.getInstance().getBankUser2().getCustomer_id();

        System.out.println(customerId+expiryDate+currencyChoosen);

        // format the date and time using a formatter


        //BankCard2 bankCard2 = new BankCard2("1111 1111 1111 1111", "EUR", "2028/04", 100,"99124010193008221210601001" );
        //Session.getInstance().getBankDatabase().addBankCard2(currencyChoosen,expiryDate,balance,customerId);
    }
    @FXML
    void onChoiceCardComboBoxClicked(MouseEvent event) {

    }
    public void updateClientScene(){
        cardNumbersAvailable.clear();
        for(BankCard2 bankCard2 : Session.getInstance().getBankUser2().getBankCards()) {
            cardNumbersAvailable.add(bankCard2.getBank_card_number());
        }
        cardChoiceComboBox.setItems(cardNumbersAvailable);
        emailLabel.setText(Session.getInstance().getBankUser2().getEmail());
        hiLabel.setText(Session.getInstance().getBankUser2().getFirst_name());
        nameLabel.setText(Session.getInstance().getBankUser2().getFirst_name()+" "+Session.getInstance().getBankUser2().getLast_name());
        moneyLabel.setText(Double.toString(Session.getInstance().getBankUser2().getBalance()));

    }

    @FXML
    void onChoiceCurrencyComboBoxClicked(MouseEvent event) {

    }

    @FXML
    void onSendButtonClicked(MouseEvent event) {

        double amount = 0.0;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid amount entered.", "");
            return;
        }
        if (amount <= 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Amount must be greater than 0.", "");
            return;
        }
        BankUser2 senderUser = Session.getInstance().getBankUser2();
        if (senderUser == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "No user selected.", "");
            return;
        }
        BankUser2 receiverUser = Session.getInstance().getBankDatabase().getBankUserByCustomerId2(accountAdressNumber.getText());
        if (receiverUser == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid receiver card number.", "");
            return;
        }
        if (senderUser.getBalance() < amount) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Insufficient funds.", "");
            return;
        }
        receiverUser.addMoney(-amount, false);
        senderUser.addMoney(amount, true);
        Session.getInstance().getBankDatabase().updateBankUser2(receiverUser);
        Session.getInstance().getBankDatabase().updateBankUser2(senderUser);

        BankTransaction transaction = new BankTransaction(senderUser.getCustomer_id(), receiverUser.getCustomer_id(), , selectedCard, receiverCard);
        Session.getInstance().getBankDatabase().addTransaction(transaction);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Success", "Money sent successfully.", "");
    }



    }
    //sprawdzenie czy można przesłać daną kwotę
    private void validatorCheck(TextField field) {
        this.validator.createCheck()
                .dependsOn("field", field.textProperty())
                .withMethod(c -> {
                    String field1 = c.get("field");
                    if(Double.parseDouble(field1)>Session.getInstance().getBankUser2().getBalance()){
                        c.error("Za mało kasy : (");
                    }else if(Double.parseDouble(field1)<0){
                        c.error("heheehe dodam se kase hehehe");
                    }
                })
                .decorates(field)
                .immediate();


    }
}
