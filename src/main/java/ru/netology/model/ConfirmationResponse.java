package ru.netology.model;

public class ConfirmationResponse {
    private String status;
    private String message;

    // Конструктор, геттеры и сеттеры
    public ConfirmationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}