package com.example.bankproject_fx.model;

public class BankCard2 {
    private String bank_card_number;
    private String currency;

    private String expiryDate;
    private double balance;
    private String customer_id;

    public BankCard2(String bank_card_number, String currency, String expiryDate, double balance, String customer_id) {
        this.bank_card_number = bank_card_number;
        this.currency = currency;
        this.expiryDate = expiryDate;
        this.balance = balance;
        this.customer_id = customer_id;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
