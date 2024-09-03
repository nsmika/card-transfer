package ru.netology.model;

public class ConfirmationRequest {
    private String transactionId;
    private String confirmationCode;

    // Конструктор, геттеры и сеттеры
    public ConfirmationRequest(String transactionId, String confirmationCode) {
        this.transactionId = transactionId;
        this.confirmationCode = confirmationCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}