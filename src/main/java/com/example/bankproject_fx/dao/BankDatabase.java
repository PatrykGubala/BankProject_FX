package com.example.bankproject_fx.dao;

import com.example.bankproject_fx.model.BankAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bankproject";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "pass";

    public BankDatabase() {

    }

    public void addBankAccount(BankAccount account) {
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bank_accounts (customer_id, account_type, balance, currency) VALUES (?, ?, ?, ?)");
            stmt.setString(1, account.getCustomerId());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.setString(4, account.getCurrency());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
    }

    public List<BankAccount> getBankAccounts(String customerId) {
        List<BankAccount> accounts = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bank_accounts WHERE customer_id = ?");
            stmt.setString(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BankAccount account = new BankAccount(
                        rs.getInt("account_id"),
                        rs.getString("customer_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance"),
                        rs.getString("currency")
                );
                accounts.add(account);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
        return accounts;
    }
}
