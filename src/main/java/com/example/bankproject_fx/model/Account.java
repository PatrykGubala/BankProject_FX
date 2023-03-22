package com.example.bankproject_fx.model;

public class Account {
    private int accountId;
    private String accountType;
    private double balance;
    private int customerId;

    public Account(int accountId, String accountType, double balance, int customerId) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
    }

}
