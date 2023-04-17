package com.example.bankproject_fx.model;

import com.example.bankproject_fx.dao.BankDatabase;
import com.example.bankproject_fx.views.CurrentChoice;
import com.example.bankproject_fx.views.Mode;
import com.example.bankproject_fx.views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Session {
    private static Session instance;

    private BankUser2 bankUser2;
    private final ViewFactory viewFactory;
    private BankDatabase bankDatabase;
    private Mode choosenMode;

    public String birthDateFormatPattern ="yyyy/MM/dd/";
    public DateFormat accountDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    private CurrentChoice currentChoice;

    private ObservableList<String> allCurrenciesAvailable = FXCollections.observableArrayList("PLN", "EUR", "YEN", "USD");

    public ObservableList<String> getAllCurrenciesAvailable() {
        return allCurrenciesAvailable;
    }

    public void setAllCurrenciesAvailable(ObservableList<String> allCurrenciesAvailable) {
        this.allCurrenciesAvailable = allCurrenciesAvailable;
    }

    public Mode getChoosenMode() {
        return choosenMode;
    }

    public void setChoosenMode(Mode choosenMode) {
        this.choosenMode = choosenMode;
    }
    private final Map<String, Double> exchangeRates;
    private final Map<String, Double> betterExchangeRates;


    private Session() {
        this.viewFactory = new ViewFactory();
        this.bankDatabase = new BankDatabase();
        choosenMode = Mode.light;
        exchangeRates = new HashMap<>();
        exchangeRates.put("PLN", 1.0);
        exchangeRates.put("USD", 4.5);
        exchangeRates.put("EUR", 5.0);
        exchangeRates.put("GBP", 5.5);
        exchangeRates.put("YEN", 100.0);

        betterExchangeRates = new HashMap<>();

        betterExchangeRates.put("PLN", 1.0);
        betterExchangeRates.put("USD", 4.0);
        betterExchangeRates.put("EUR", 4.5);
        betterExchangeRates.put("GBP", 5.0);
        betterExchangeRates.put("YEN", 90.0);

    }

    public Map<String, Double> getBetterExchangeRates() {
        return betterExchangeRates;
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }







    public void setBankUser2(BankUser2 bankUser2) {
        this.bankUser2 = bankUser2;
    }

    public BankUser2 getBankUser2() {
        return bankUser2;
    }

}