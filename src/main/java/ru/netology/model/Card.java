package ru.netology.model;

public class Card {
    private String number;
    private String validTill;
    private String cvv;
    private double balance;

    public Card(String number, String validTill, String cvv, double balance) {
        this.number = number;
        this.validTill = validTill;
        this.cvv = cvv;
        this.balance = balance;
    }

    // Геттеры и сеттеры
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}