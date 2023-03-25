package com.example.bankproject_fx.model;

import com.example.bankproject_fx.views.ViewFactory;

public class Session {
    private static Session instance;
    private BankUser bankUser;
    private final ViewFactory viewFactory;

    private Session() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }
    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public BankUser getBankUser() {
        return bankUser;
    }

    public void setBankUser(BankUser bankUser) {
        this.bankUser = bankUser;
    }

    public void clear() {
        bankUser = null;
    }
}