package org.example.receiptprocessorchallenge.repository;

import org.example.receiptprocessorchallenge.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReceiptRepository {

    private final List<Receipt> receipts = new ArrayList<>();

    public List<Receipt> findAll() {
        return receipts;
    }

    public Optional<Receipt> findById(String id) {
        return receipts.stream()
                .filter(receipt -> receipt.getId().equals(id))
                .findFirst();
    }

    public Receipt save(Receipt receipt) {
        receipts.add(receipt);
        return receipt;
    }
}