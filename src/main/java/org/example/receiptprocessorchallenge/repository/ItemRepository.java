package org.example.receiptprocessorchallenge.repository;

import org.example.receiptprocessorchallenge.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemRepository {

    private final List<Item> items = new ArrayList<>();

    public List<Item> findAll() {
        return items;
    }

    public Optional<Item> findById(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public Item save(Item item) {
        items.add(item);
        return item;
    }
}
