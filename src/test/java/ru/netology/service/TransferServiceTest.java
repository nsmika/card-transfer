package ru.netology.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.model.ConfirmationRequest;
import ru.netology.model.ConfirmationResponse;
import ru.netology.model.TransferRequest;
import ru.netology.model.TransferResponse;
import ru.netology.repository.CardRepository;
import ru.netology.repository.LogRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransferServiceTest {

    @InjectMocks
    private TransferService transferService;

    @Mock
    private LogRepository logRepository;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processTransferTest() {
        // Создание тестовых данных
        TransferRequest request = new TransferRequest(
                "1111222233334444",
                "12/25",
                "123",
                "5555666677778888",
                1000.0
        );

        TransferResponse expectedResponse = new TransferResponse(
                "Success",
                1000.0,
                10.0
        );

        // Мокирование поведения методов
        when(cardRepository.isCardValid(request.getCardFromNumber(), request.getCardFromValidTill(), request.getCardFromCVV())).thenReturn(true);
        when(cardRepository.isCardValid(request.getCardToNumber(), null, null)).thenReturn(true);
        when(cardRepository.getBalance(request.getCardFromNumber())).thenReturn(BigDecimal.valueOf(5000.0));
        when(cardRepository.getFee(BigDecimal.valueOf(request.getAmount()))).thenReturn(BigDecimal.valueOf(10.0));
        doNothing().when(cardRepository).updateBalance(anyString(), any(BigDecimal.class));

        // Запуск метода
        TransferResponse actualResponse = transferService.processTransfer(request);

        // Проверка результатов
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.getAmount(), actualResponse.getAmount());
        assertEquals(expectedResponse.getFee(), actualResponse.getFee());

        // Проверка, что метод логирования был вызван
        verify(logRepository, times(1)).logTransfer(any(TransferRequest.class), any(TransferResponse.class));
    }

    @Test
    void confirmOperationTest() {
        // Создание тестовых данных
        ConfirmationRequest request = new ConfirmationRequest("transaction123", "valid-code");
        ConfirmationResponse expectedResponse = new ConfirmationResponse("Success", "Operation confirmed.");

        // Запуск метода
        ConfirmationResponse actualResponse = transferService.confirmOperation(request);

        // Проверка результатов
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
    }
}