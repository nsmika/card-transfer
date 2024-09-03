package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.model.ConfirmationRequest;
import ru.netology.model.ConfirmationResponse;
import ru.netology.model.TransferRequest;
import ru.netology.model.TransferResponse;
import ru.netology.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request) {
        TransferResponse response = transferService.processTransfer(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<ConfirmationResponse> confirm(@RequestBody ConfirmationRequest request) {
        ConfirmationResponse response = transferService.confirmOperation(request);
        return ResponseEntity.ok(response);
    }
}