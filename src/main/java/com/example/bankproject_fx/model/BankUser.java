package com.example.bankproject_fx.model;

import java.util.List;

public class BankUser {
    private String id;
    private String imie;
    private String naziwsko;
    private int saldo;
    private String ulica;
    private String miasto;

    private String numerDomu;
    private String dataUrodzenia;

    private List<Account> accounts;

    @Override
    public String toString() {
        return "BankUser{" +
                "id='" + id + '\'' +
                ", imie='" + imie + '\'' +
                ", naziwsko='" + naziwsko + '\'' +
                ", saldo=" + saldo +
                ", ulica='" + ulica + '\'' +
                ", miasto='" + miasto + '\'' +
                ", numerDomu='" + numerDomu + '\'' +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                '}';
    }

    public BankUser( String imie, String naziwsko, int saldo, String ulica, String miasto, String numerDomu, String dataUrodzenia, List<Account> accounts) {
        this.id = id;
        this.imie = imie;
        this.naziwsko = naziwsko;
        this.saldo = saldo;
        this.ulica = ulica;
        this.miasto = miasto;
        this.numerDomu = numerDomu;
        this.dataUrodzenia = dataUrodzenia;
        this.accounts = accounts;
    }
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNaziwsko() {
        return naziwsko;
    }

    public void setNaziwsko(String naziwsko) {
        this.naziwsko = naziwsko;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
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

    public String getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(String numerDomu) {
        this.numerDomu = numerDomu;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }
}
