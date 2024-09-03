package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.TransferRequest;
import ru.netology.model.TransferResponse;

@Repository
public class LogRepository {
    public void logTransfer(TransferRequest request, TransferResponse response) {
        // Логирование в файл или консоль
        System.out.println("Transfer logged: " + request.getCardFromNumber() +
                " -> " + request.getCardToNumber() + " Amount: " + response.getAmount() +
                " Fee: " + response.getFee() + " Message: " + response.getMessage());
    }
}