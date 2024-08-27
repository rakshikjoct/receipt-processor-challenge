package org.example.receiptprocessorchallenge.controller;

import org.example.receiptprocessorchallenge.dto.PointsResponse;
import org.example.receiptprocessorchallenge.model.Receipt;
import org.example.receiptprocessorchallenge.repository.ReceiptRepository;
import org.example.receiptprocessorchallenge.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptController(ReceiptService receiptService, ReceiptRepository receiptRepository) {
        this.receiptService = receiptService;
        this.receiptRepository = receiptRepository;
    }

    @PostMapping("/receipts/process")
    public ResponseEntity<String> processReceipt(@RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        return new ResponseEntity<>( "{\"id\":\"" + id + "\"}", HttpStatus.CREATED);
    }

    @GetMapping("/receipts/{id}/points")
    public ResponseEntity<PointsResponse> getPoints(@PathVariable String id) {
        Receipt receipt = receiptRepository.findById(id).orElse(null);
        if (receipt == null) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.WARNING, "Receipt with ID {0} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int points = receiptService.calculatePoints(receipt);
        PointsResponse response = new PointsResponse(points);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}