package com.example.bankproject_fx.model;

import java.util.concurrent.atomic.AtomicInteger;

public class BankUser {
    private int customer_id;
    private String imie;
    private String nazwisko;
    private String email;
    private String numerTelefonu;

    private double saldo;
    private String ulica;
    private String miasto;

    private String kodPocztowy;
    private String dataUrodzenia;


    @Override
    public String toString() {
        return "BankUser{" +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", numerTelefonu='" + numerTelefonu + '\'' +
                ", saldo=" + saldo +
                ", ulica='" + ulica + '\'' +
                ", miasto='" + miasto + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                '}';
    }

    public BankUser(String imie, String nazwisko, String email, String numerTelefonu, double saldo, String ulica, String miasto, String kodPocztowy, String dataUrodzenia) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.numerTelefonu = numerTelefonu;
        this.saldo = saldo;
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.dataUrodzenia = dataUrodzenia;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }





    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
