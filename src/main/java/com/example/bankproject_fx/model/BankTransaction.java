package com.example.bankproject_fx.model;

import java.util.Date;

public class BankTransaction {
    private String from;
    private String to;
    private String date;
    private String message;
    private double amount;

    public BankTransaction(String from, String to, String date, String message, double amount) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.message = message;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
