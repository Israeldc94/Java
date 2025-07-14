package com.example.inventory_capstone.repository;
import com.example.inventory_capstone.model.InventoryItem;

import java.util.List;

public interface InventoryRepository {
    List<InventoryItem> getAll();

    void add(InventoryItem item);

    void update(InventoryItem item);

    void delete(String productID);

    InventoryItem getByID(String productID);

}