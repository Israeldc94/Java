package com.example.inventory_capstone.service;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.PerishableProduct;
import com.example.inventory_capstone.model.Product;
import com.example.inventory_capstone.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
        return sortInventoryByProductID(inventoryRepository.getAll().stream().filter(item -> item.getQuantity() > 0)
                .filter(item -> {
                    Product product = item.getProduct();
                    if (product instanceof PerishableProduct pp) {
                        return !pp.getExpirationDate().isBefore(LocalDate.now());
                    }
                    return true;
                })
                .collect(Collectors.toList()));
    }

    public void updateOrAddItem(InventoryItem newItem) {
        if (newItem == null) {
            throw new IllegalArgumentException("No item detected");
        }


        String productID = newItem.getProduct().getProductID();
        InventoryItem existingItem = inventoryRepository.getByID(productID);

        if (existingItem != null) {
            int updatedQuantity = existingItem.getQuantity() + newItem.getQuantity();
            BigDecimal updatedPrice = newItem.getPrice();

            Product existingProduct = existingItem.getProduct();
            Product newProduct = newItem.getProduct();
            Product finalProduct;

            if (existingProduct instanceof PerishableProduct && newProduct instanceof PerishableProduct) {

                LocalDate newExpiration = ((PerishableProduct) newProduct).getExpirationDate();
                finalProduct = new PerishableProduct(productID, newProduct.getProductName(), newExpiration);
            }else {

                finalProduct = new Product(productID, newProduct.getProductName());
            }

            InventoryItem mergedItem = new InventoryItem(finalProduct,updatedQuantity, updatedPrice);
            inventoryRepository.update(mergedItem);
        } else {
            inventoryRepository.add(newItem);
        }
    }

    public void removeItem(String ProductID) {
        inventoryRepository.delete(ProductID);
    }

    public InventoryItem getItem(String productID) {
        return inventoryRepository.getByID(productID);
    }

    public List<InventoryItem> getAllItems() {
        return sortInventoryByProductID(inventoryRepository.getAll());
    }

    public List<InventoryItem> sortInventoryByProductID(List<InventoryItem> items) {
        return items.stream().sorted(Comparator.comparing(item -> item.getProduct().getProductID()))
                .collect(Collectors.toList());
    }

}
