package com.example.bankproject_fx.controllers;


import com.example.bankproject_fx.model.*;
import com.example.bankproject_fx.views.AlertHelper;
import com.example.bankproject_fx.views.Mode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import net.synedra.validatorfx.Validator;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ClientMainPageController implements Initializable {

    @FXML
    private Button TopUpCardButton;

    @FXML
    private TextField accountAdressNumber;

    @FXML
    private Button addCardButton;

    @FXML
    private TextField amountAddCardField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField amountTopUpCardField;

    @FXML
    private ComboBox<String> cardChoiceTopUpComboBox;

    @FXML
    private Label choosenCardCurrency;

    @FXML
    private ChoiceBox<String> currencyChoiceAddCardField;

    @FXML
    private ChoiceBox<String> currencyChoiceField;

    @FXML
    private Label currencyTopUpLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label hiLabel;


    @FXML
    private Label transferErrorLabel;
    @FXML
    private Label transferAmountInPLNLabel;
    @FXML
    private Label topUpCardInPLN;
    @FXML
    private TextArea messageField;

    @FXML
    private Label moneyLabel;

    @FXML
    private Label nameLabel;
    @FXML
    private Label topUpCardInPLNCurrency;
    @FXML
    private Label transferAmountInPLNCurrencyLabel;

    @FXML
    private Button sendMoneyButton;
    @FXML
    private TextField transferAmountInPLN;

    @FXML
    private AnchorPane mainWindow;
    @FXML
    private Button refreshButton;
    private ObservableList<String> cardNumbersAvailable = FXCollections.observableArrayList();
    private ObservableList<String> currenciesAvailable = FXCollections.observableArrayList();
    private ObservableList<String> currenciesAll = FXCollections.observableArrayList();


    private Validator validator = new Validator();

    public ClientMainPageController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currencyChoiceField.valueProperty().addListener((observable, oldValue, newValue) -> handleInputChange());
        amountField.textProperty().addListener((observable, oldValue, newValue) -> handleInputChange());
        amountTopUpCardField.textProperty().addListener((observable, oldValue, newValue) -> handleTopUpInputChange());
        cardChoiceTopUpComboBox.valueProperty().addListener((observable, oldValue, newValue) -> handleTopUpInputChange());
        cardChoiceTopUpComboBox.valueProperty().addListener((observable, oldValue, newValue) -> handleTopUpCardInputChange());
        amountField.textProperty().addListener((observable, oldValue, newValue) -> handleInputChange());
        //amountAddCardField.textProperty().addListener((observable, oldValue, newValue) -> handleAmountInputChange(amountAddCardField,currencyChoiceAddCardField.getValue(),"betterExchangeRates" ));

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
        accountAdressNumber.textProperty().addListener((observable, oldValue, newValue) -> {
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
        });

        validatorCheck(amountField);
        validatorCheck(amountTopUpCardField);
        validatorCheck(amountAddCardField);



        updateClientScene();







    }
    @FXML
    void onRefreshButtonClicked(MouseEvent event) {
        updateClientScene();
    }
    @FXML
    void onAddCardButtonClicked(MouseEvent event) {
        double amount = 0.0;
        String currencyChoosen= currencyChoiceAddCardField.getValue();

        try {
            System.out.println(currencyChoosen);
            amount = Double.parseDouble(amountAddCardField.getText()) * Session.getInstance().getBetterExchangeRates().get(currencyChoosen);
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

        if (senderUser.getBalance() < amount) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Insufficient funds.", "");
            return;
        }


        senderUser.setBalance(senderUser.getBalance()-amount);
        Session.getInstance().getBankDatabase().updateBankUser2Balance(senderUser);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        BankTransaction transaction = new BankTransaction(senderUser.getCustomer_id(), senderUser.getCustomer_id(),formattedDateTime,messageField.getText(),amount);
        Session.getInstance().getBankDatabase().addBankTransaction(transaction);

        now = LocalDateTime.now();
        DateTimeFormatter cardFormatter = DateTimeFormatter.ofPattern("yyyy/MM");
        String expiryDate = now.format(cardFormatter);
        String customerId = Session.getInstance().getBankUser2().getCustomer_id();

        System.out.println(customerId+expiryDate+currencyChoosen);

        // format the date and time using a formatter

        Session.getInstance().getBankDatabase().addBankCard2(currencyChoosen,expiryDate,amount,customerId);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Success", "Money sent successfully.", "");
        updateClientScene();


    }
    @FXML
    void onChoiceCardComboBoxClicked(MouseEvent event) {

    }
    public void updateClientScene(){
        try{
            currencyChoiceField.setItems(Session.getInstance().getAllCurrenciesAvailable());
            currencyChoiceField.getSelectionModel().selectFirst();
            cardChoiceTopUpComboBox.setItems(cardNumbersAvailable);
            cardChoiceTopUpComboBox.getSelectionModel().selectFirst();

            currencyChoiceAddCardField.setItems(Session.getInstance().getAllCurrenciesAvailable());
            currencyChoiceAddCardField.getSelectionModel().selectFirst();


            cardNumbersAvailable.clear();
            for(BankCard2 bankCard2 : Session.getInstance().getBankUser2().getBankCards()) {
                cardNumbersAvailable.add(bankCard2.getBank_card_number());
            }
            cardChoiceTopUpComboBox.setItems(cardNumbersAvailable);
            cardChoiceTopUpComboBox.getSelectionModel().selectFirst();
            emailLabel.setText(Session.getInstance().getBankUser2().getEmail());
            hiLabel.setText(Session.getInstance().getBankUser2().getFirst_name());
            nameLabel.setText(Session.getInstance().getBankUser2().getFirst_name()+" "+Session.getInstance().getBankUser2().getLast_name());
            moneyLabel.setText(Double.toString(Session.getInstance().getBankUser2().getBalance()));
        }catch(Exception e){
            return;
        }


    }
    @FXML
    void onTopUpCardButtonClicked(MouseEvent event) {
        double amount = 0.0;
        BankCard2 bankCard2 = Session.getInstance().getBankDatabase().getBankCard2ByBankCardNumber(cardChoiceTopUpComboBox.getValue().replaceAll("\\s", ""));
        if (bankCard2 == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid bank card number.", "");
            return;
        }
        try {
            System.out.println(bankCard2.getCurrency());
            amount = Double.parseDouble(amountTopUpCardField.getText()) * Session.getInstance().getBetterExchangeRates().get(bankCard2.getCurrency());
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

        if (senderUser.getBalance() < amount) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Insufficient funds.", "");
            return;
        }

        bankCard2.setBalance(bankCard2.getBalance()+amount);
        senderUser.setBalance(senderUser.getBalance()-amount);
        Session.getInstance().getBankDatabase().updateBankCard2Balance(bankCard2);
        Session.getInstance().getBankDatabase().updateBankUser2Balance(senderUser);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        BankTransaction transaction = new BankTransaction(senderUser.getCustomer_id(), bankCard2.getCustomer_id(),formattedDateTime,messageField.getText(),amount);
        Session.getInstance().getBankDatabase().addBankTransaction(transaction);

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Success", "Money sent successfully.", "");


        updateClientScene();
        //add code to update transactions list int the TransactionsPageController

    }

    @FXML
    void onChoiceCurrencyComboBoxClicked(MouseEvent event) {

    }

    public void handleAmountInputChange(TextField amountField,String currencyChoice ,String exchangeRates) {
        if(exchangeRates.equals("betterExchangeRates") ){
            try {
                double amount = Double.parseDouble(amountField.getText())*Session.getInstance().getBetterExchangeRates().get(currencyChoice);
                //transferAmountInPLNLabel.setText(String.valueOf(amount));

            } catch (NumberFormatException e) {
                return;
            }
        }else if(exchangeRates.equals("exchangeRates") ){
            try {
                double amount = Double.parseDouble(amountField.getText())*Session.getInstance().getExchangeRates().get(currencyChoice);
                //transferAmountInPLNLabel.setText(String.valueOf(amount));

            } catch (NumberFormatException e) {
                return;
            }
        }

    }
    public void handleInputChange() {
        try {
        double amount = Double.parseDouble(amountField.getText())*Session.getInstance().getExchangeRates().get(currencyChoiceField.getValue());
        transferAmountInPLNLabel.setText(String.valueOf(amount));

        } catch (NumberFormatException e) {
             return;
        }
    }


    public void handleTopUpInputChange() {
        try {
        double amount = Double.parseDouble(amountTopUpCardField.getText())*Session.getInstance().getBetterExchangeRates().get(Session.getInstance().getBankDatabase().getBankCard2ByBankCardNumber(cardChoiceTopUpComboBox.getValue()).getCurrency());
            topUpCardInPLN.setText(String.valueOf(amount));
        } catch (NumberFormatException e) {
            return;
        }

    }
    public void handleTopUpCardInputChange() {
        try{
            choosenCardCurrency.setText(Session.getInstance().getBankDatabase().getBankCard2ByBankCardNumber(cardChoiceTopUpComboBox.getValue()).getCurrency());
        }catch(Exception e) {
            return;
        }
    }



    @FXML
    void onSendButtonClicked(MouseEvent event) {

        double amount = 0.0;

        try {
            amount = Double.parseDouble(amountField.getText())*Session.getInstance().getExchangeRates().get(currencyChoiceField.getValue());
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
        BankUser2 receiverUser = Session.getInstance().getBankDatabase().getBankUserByCustomerId2(accountAdressNumber.getText().replaceAll("\\s", ""));
        if (receiverUser == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Invalid receiver account number.", "");
            return;
        }
        if (senderUser.getBalance() < amount) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Insufficient funds.", "");
            return;
        }
        receiverUser.setBalance(receiverUser.getBalance()+amount);
        senderUser.setBalance(senderUser.getBalance()-amount);
        Session.getInstance().getBankDatabase().updateBankUser2Balance(receiverUser);
        Session.getInstance().getBankDatabase().updateBankUser2Balance(senderUser);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        BankTransaction transaction = new BankTransaction(senderUser.getCustomer_id(), receiverUser.getCustomer_id(),formattedDateTime,messageField.getText(),amount);
        Session.getInstance().getBankDatabase().addBankTransaction(transaction);
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Success", "Money sent successfully.", "");


        updateClientScene();

    }




    //sprawdzenie czy można przesłać daną kwotę
    private void validatorCheck(TextField field) {
        this.validator.createCheck()
                .dependsOn("field", field.textProperty())
                .withMethod(c -> {
                    String field1 = c.get("field");
                    try{
                        if(Double.parseDouble(field1)*Session.getInstance().getBetterExchangeRates().get(currencyChoiceField.getValue())>Session.getInstance().getBankUser2().getBalance()){
                            c.error("Za mało kasy : (");
                        }else if(Double.parseDouble(field1)<0){
                            c.error("heheehe dodam se kase hehehe");
                        }
                    }catch(NumberFormatException e){
                        c.error("hehehe");
                    }

                })
                .decorates(field)
                .immediate();


    }
}
