package com.example.capstone_inventory.service;

import com.example.capstone_inventory.model.InventoryItem;
import com.example.capstone_inventory.model.PerishableProduct;
import com.example.capstone_inventory.model.Product;
import com.example.capstone_inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryItem> getCustomerAvailableInventory() {
        return inventoryRepository.getAll().stream().filter(item -> item.getQuantity() > 0)
                .filter(item -> {
                    Product product = item.getProduct();
                    if (product instanceof PerishableProduct pp) {
                        return !pp.getExpirationDate().isBefore(LocalDate.now());
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
    public void updateOrAddItem(InventoryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("No item detected");
        }

        String productID = item.getProduct().getProductID();
        InventoryItem existingItem = inventoryRepository.getByID(productID);

        if (existingItem != null) {
            existingItem.setQuantity(item.getQuantity());
            existingItem.setPrice(item.getPrice());
            inventoryRepository.update(existingItem);
        } else {
            inventoryRepository.add(item);
        }
    }

    public void removeItem(String ProductID) {
        inventoryRepository.delete(ProductID);
    }

    public InventoryItem getItem(String productID) {
        return inventoryRepository.getByID(productID);
    }
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.getAll();
    }
}
