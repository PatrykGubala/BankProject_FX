package com.example.bankproject_fx.dao;

import com.example.bankproject_fx.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bankproject";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "pass";

    private static final String INSERT_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (customer_id, account_type, balance, currency) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BANK_ACCOUNTS_QUERY = "SELECT * FROM bank_accounts WHERE customer_id = ?";
    private static final String INSERT_BANK_USER_QUERY = "INSERT INTO bank_users (imie, nazwisko,email,numerTelefonu, saldo, ulica, miasto, kodPocztowy, dataUrodzenia) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BANK_USERS_BY_EMAIL_QUERY = "SELECT * FROM bank_users WHERE email = ?";


    private static final String INSERT_BANK_ACCOUNT_2_QUERY = "INSERT INTO bank_account_2( first_name, last_name, email, password,phone_number, street, city, zip_code,customer_birth_rate, balance ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BANK_ACCOUNT_2_QUERY = "SELECT * FROM bank_account_2 WHERE email = ? AND password = ?";

    private static final String INSERT_BANK_CARD_2_QUERY = "INSERT INTO bank_card_2 (customer_id,expiry_date,currency, balance) VALUES (?, ?, ?, ?)";
    private static final String SELECT_BANK_CARD_2_QUERY = "SELECT * FROM  bank_card_2 WHERE customer_id = ?";
    private static final String INSERT_BANK_TRANSACTION_2_QUERY = "INSERT INTO bank_transaction_2( customer_from_id, customer_to_id, transaction_date, amount,message) VALUES (?, ?, ?, ?, ?)";


    public BankDatabase() {

    }


    public void addBankAccount2(BankUser2 bankUser) {
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(INSERT_BANK_ACCOUNT_2_QUERY);
            stmt.setString(1, bankUser.getFirst_name());
            stmt.setString(2, bankUser.getLast_name());
            stmt.setString(3, bankUser.getEmail());
            stmt.setString(4, bankUser.getPassword());
            stmt.setString(5, bankUser.getPhone_number());
            stmt.setString(6, bankUser.getStreet());
            stmt.setString(7, bankUser.getCity());
            stmt.setString(8, bankUser.getZip_code());
            stmt.setString(9, bankUser.getCustomer_birth_date());
            stmt.setDouble(10, bankUser.getBalance());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
    }

    public List<BankUser2> getBankAccountByEmail2(String email) {
        List<BankUser2> accounts = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_BANK_ACCOUNT_2_QUERY);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BankUser2 bankUser = new BankUser2(
                        rs.getString("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("zip_code"),
                        rs.getString("customer_birth_date"),
                        rs.getDouble("balance")
                );
                accounts.add(bankUser);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
        return accounts;
    }

    public void addBankCard2(String currency, String expiryDate, double balance, String customer_id) {
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(INSERT_BANK_CARD_2_QUERY);
            stmt.setString(1, customer_id);
            stmt.setString(2, expiryDate);
            stmt.setString(3, currency);
            stmt.setDouble(4, balance);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
    }



    public List<BankCard2> getBankCard2ByCustomerId(String customer_id) {
        List<BankCard2> cards = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_BANK_CARD_2_QUERY);
            stmt.setString(1, customer_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BankCard2 bankCard2 = new BankCard2(
                        rs.getString("bank_card_number"),
                        rs.getString("currency"),
                        rs.getString("expiry_date"),
                        rs.getDouble("balance"),
                        rs.getString("customer_id")

                );
                cards.add(bankCard2);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
        return cards;
    }


    public void addBankTransaction(BankTransaction bankTransaction) {
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(INSERT_BANK_TRANSACTION_2_QUERY);
            stmt.setString(1, bankTransaction.getFrom());
            stmt.setString(2, bankTransaction.getTo());
            stmt.setString(3, bankTransaction.getDate());
            stmt.setDouble(4, bankTransaction.getAmount());
            stmt.setString(5, bankTransaction.getMessage());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
    }


    public boolean credentialsConfirmation2(String login, String password){
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_BANK_ACCOUNT_2_QUERY);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {

                BankUser2 bankUser2 = new BankUser2(
                        rs.getString("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone_number"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("zip_code"),
                        rs.getString("customer_birth_date"),
                        rs.getDouble("balance")
                );
                Session.getInstance().setBankUser2(bankUser2);
                Session.getInstance().getBankUser2().setBankCards(getBankCard2ByCustomerId(bankUser2.getCustomer_id()));
                rs.close();
                stmt.close();
                return true;
            }



        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
            return false;
        }


        return false;
    }






    public boolean isPekaoAccountNumberValid(String accountNumber) {
        // sprawdzenie długości numeru konta
        if (accountNumber.length() != 26) {
            return false;
        }

        // sprawdzenie formatu numeru konta
        if (!accountNumber.matches("[0-9]+")) {
            return false;
        }

        // obliczenie sumy kontrolnej
        String bankCode = "1240";
        String accountCode = accountNumber.substring(2, 26);

        String checkString = accountCode + bankCode + "00";
        long checkValue = Long.parseLong(checkString) % 97;

        // sprawdzenie sumy kontrolnej
        return checkValue == 1;
    }
}
