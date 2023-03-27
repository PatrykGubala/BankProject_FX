package com.example.bankproject_fx.dao;

import com.example.bankproject_fx.model.BankUsers;
import com.example.bankproject_fx.model.BankUser;
import com.example.bankproject_fx.model.Session;

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


    public BankDatabase() {

    }
    public void addBankUser(BankUser bankUser) {
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(INSERT_BANK_USER_QUERY);
            stmt.setString(1, bankUser.getImie());
            stmt.setString(2, bankUser.getNazwisko());
            stmt.setString(3, bankUser.getEmail());
            stmt.setString(4, bankUser.getNumerTelefonu());
            stmt.setDouble(5, bankUser.getSaldo());
            stmt.setString(6, bankUser.getUlica());
            stmt.setString(7, bankUser.getMiasto());
            stmt.setString(8, bankUser.getKodPocztowy());
            stmt.setString(9, bankUser.getDataUrodzenia());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }
    }

    public List<BankUser> getBankUserByEmail(String email) {
        List<BankUser> accounts = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_BANK_USERS_BY_EMAIL_QUERY);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BankUser bankUser = new BankUser(
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        rs.getString("email"),
                        rs.getString("numerTelefonu"),
                        rs.getDouble("saldo"),
                        rs.getString("ulica"),
                        rs.getString("miasto"),
                        rs.getString("kodPocztowy"),
                        rs.getString("dataUrodzenia")
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

    public void addBankAccount(BankUsers account) {
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(INSERT_BANK_ACCOUNT_QUERY);
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

    public List<BankUsers> getBankAccounts(String email) {
        List<BankUsers> accounts = new ArrayList<>();
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_BANK_ACCOUNTS_QUERY);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BankUsers account = new BankUsers(
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

    public boolean credentialsConfirmation(String login, String password){
        try (Connection conn = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)){
            PreparedStatement stmt = conn.prepareStatement(SELECT_BANK_USERS_BY_EMAIL_QUERY);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                BankUser bankUser = new BankUser(
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        rs.getString("email"),
                        rs.getString("numerTelefonu"),
                        rs.getDouble("saldo"),
                        rs.getString("ulica"),
                        rs.getString("miasto"),
                        rs.getString("kodPocztowy"),
                        rs.getString("dataUrodzenia")
                );
                System.out.println(password+bankUser.getNazwisko());
                if(password.equals(bankUser.getNazwisko())){
                    Session.getInstance().setBankUser(bankUser);
                    rs.close();
                    stmt.close();
                    return true;
                }
                rs.close();
                stmt.close();
                return false;
            }

        } catch (SQLException ex) {
            System.out.println("SQL error: " + ex.getMessage());
        }


        return false;
    }
}
