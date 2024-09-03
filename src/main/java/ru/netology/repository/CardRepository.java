package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Card;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CardRepository {

    private final Map<String, Card> cardStorage = new HashMap<>();

    public CardRepository() {
        // Инициализация данных о картах для примера
        cardStorage.put("1111222233334444", new Card("1111222233334444", "12/25", "123", 10000.0));
        cardStorage.put("5555666677778888", new Card("5555666677778888", "11/24", "456", 5000.0));
    }

    public boolean isCardValid(String cardNumber, String validTill, String cvv) {
        Card card = cardStorage.get(cardNumber);
        return card != null && card.getValidTill().equals(validTill) && card.getCvv().equals(cvv);
    }

    public BigDecimal getBalance(String cardNumber) {
        Card card = cardStorage.get(cardNumber);
        return card != null ? BigDecimal.valueOf(card.getBalance()) : BigDecimal.ZERO;
    }

    public void updateBalance(String cardNumber, BigDecimal newBalance) {
        Card card = cardStorage.get(cardNumber);
        if (card != null) {
            card.setBalance(newBalance.doubleValue());
        }
    }

    public BigDecimal getFee(BigDecimal amount) {
        // Например, комиссия 1% от суммы перевода
        return amount.multiply(BigDecimal.valueOf(0.01));
    }
}