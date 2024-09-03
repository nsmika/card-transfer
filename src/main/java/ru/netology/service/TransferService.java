package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.model.ConfirmationRequest;
import ru.netology.model.ConfirmationResponse;
import ru.netology.model.TransferRequest;
import ru.netology.model.TransferResponse;
import ru.netology.repository.CardRepository;
import ru.netology.repository.LogRepository;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final CardRepository cardRepository;
    private final LogRepository logRepository;

    @Autowired
    public TransferService(CardRepository cardRepository, LogRepository logRepository) {
        this.cardRepository = cardRepository;
        this.logRepository = logRepository;
    }

    public TransferResponse processTransfer(TransferRequest request) {
        // Проверка валидности карт
        boolean isCardFromValid = cardRepository.isCardValid(request.getCardFromNumber(), request.getCardFromValidTill(), request.getCardFromCVV());
        boolean isCardToValid = cardRepository.isCardValid(request.getCardToNumber(), null, null);

        if (!isCardFromValid || !isCardToValid) {
            return new TransferResponse("Invalid Card", request.getAmount(), 0.0);
        }

        // Получение баланса и расчет комиссии
        BigDecimal fromCardBalance = cardRepository.getBalance(request.getCardFromNumber());
        BigDecimal amount = BigDecimal.valueOf(request.getAmount());
        BigDecimal fee = cardRepository.getFee(amount);
        BigDecimal totalAmount = amount.add(fee);

        if (fromCardBalance.compareTo(totalAmount) < 0) {
            return new TransferResponse("Insufficient Funds", request.getAmount(), fee.doubleValue());
        }

        // Обновление балансов
        cardRepository.updateBalance(request.getCardFromNumber(), fromCardBalance.subtract(totalAmount));
        BigDecimal toCardBalance = cardRepository.getBalance(request.getCardToNumber()).add(amount);
        cardRepository.updateBalance(request.getCardToNumber(), toCardBalance);

        // Логирование
        TransferResponse response = new TransferResponse("Success", request.getAmount(), fee.doubleValue());
        logRepository.logTransfer(request, response);

        return response;
    }

    public ConfirmationResponse confirmOperation(ConfirmationRequest request) {
        if ("valid-code".equals(request.getConfirmationCode())) {
            return new ConfirmationResponse("Success", "Operation confirmed.");
        } else {
            return new ConfirmationResponse("Failure", "Invalid confirmation code.");
        }
    }
}