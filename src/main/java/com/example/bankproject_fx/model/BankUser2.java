package com.example.bankproject_fx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BankUser2 {
    private String customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String phone_number;
    private String street;
    private String city;

    private List<BankCard2> bankCards;

    private String zip_code;
    private String customer_birth_date;

    public BankUser2(String customer_id, String first_name, String last_name, String email, String password, String phone_number, String street, String city, String zip_code, String customer_birth_date, double balance) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.street = street;
        this.city = city;
        this.zip_code = zip_code;
        this.customer_birth_date = customer_birth_date;
        this.balance = balance;
        bankCards = new ArrayList<BankCard2>();



    }

    private double balance;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCustomer_birth_date() {
        return customer_birth_date;
    }

    public void setCustomer_birth_date(String customer_birth_date) {
        this.customer_birth_date = customer_birth_date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<BankCard2> getBankCards() {
        return bankCards;
    }

    public void setBankCards(List<BankCard2> bankCards) {
        this.bankCards = bankCards;
    }
}
