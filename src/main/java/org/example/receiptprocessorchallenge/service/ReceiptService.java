package org.example.receiptprocessorchallenge.service;

import org.example.receiptprocessorchallenge.model.Item;
import org.example.receiptprocessorchallenge.model.Receipt;
import org.example.receiptprocessorchallenge.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.UUID;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipt.setId(id);
        receiptRepository.save(receipt);
        return id;
    }

    public int calculatePoints(Receipt receipt) {
        int points = 0;

        // Rule 1: One point for every alphanumeric character in the retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // Rule 2: 50 points if the total is a round dollar amount with no cents
        if (receipt.getTotal().setScale(2, RoundingMode.UNNECESSARY).toString().endsWith(".00")) {
            points += 50;
        }

        // Rule 3: 25 points if the total is a multiple of 0.25
        if ((receipt.getTotal().doubleValue() * 4) % 1 == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items on the receipt
        points += (int) Math.floor(receipt.getItems().size() / 2.0) * 5;

        // Rule 5: If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer
        for (Item item : receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += (int) Math.ceil(item.getPrice().doubleValue() * 0.2);
            }
        }

        // Rule 6: 6 points if the day in the purchase date is odd
        if (receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            points += 6;
        }

        // Rule 7: 10 points if the time of purchase is after 2:00pm and before 4:00pm
        if (receipt.getPurchaseTime().getHour() >= 14 && receipt.getPurchaseTime().getHour() < 16) {
            points += 10;
        }

        return points;
    }
}