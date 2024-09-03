package ru.netology.model;

public class TransferResponse {
    private String message;
    private double amount;
    private double fee;

    public TransferResponse(String message, double amount, double fee) {
        this.message = message;
        this.amount = amount;
        this.fee = fee;
    }

    // Геттеры и сеттеры
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}