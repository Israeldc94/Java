package com.example.capstone_inventory.repository;

import com.example.capstone_inventory.model.InventoryItem;

import java.util.List;

public interface InventoryRepository {
    List<InventoryItem> getAll();

    void add(InventoryItem item);

    void update(InventoryItem item);

    void delete(String productID);

    InventoryItem getByID(String productID);

}
