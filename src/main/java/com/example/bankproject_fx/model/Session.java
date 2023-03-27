package com.example.bankproject_fx.model;

import com.example.bankproject_fx.dao.BankDatabase;
import com.example.bankproject_fx.views.ViewFactory;

public class Session {
    private static Session instance;
    private BankUser bankUser;
    private final ViewFactory viewFactory;
    private BankDatabase bankDatabase;
    private Session() {
        this.viewFactory = new ViewFactory();
        this.bankDatabase = new BankDatabase();
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