package com.example.inventory_capstone.repository;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.PerishableProduct;
import com.example.inventory_capstone.model.Product;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SampleInventoryRepository implements InventoryRepository {

    private final HashMap<String, InventoryItem> inventory = new HashMap<>();

    public SampleInventoryRepository() {
        initializeSample();
    }

    private void initializeSample() {
        //add non perishables
        addSampleProduct("0001", "Paper Towels", 30, BigDecimal.valueOf(5.99));
        addSampleProduct("0002", "Toilet Paper", 30, BigDecimal.valueOf(3.99));
        addSampleProduct("0003", "Wipes", 30, BigDecimal.valueOf(1.99));
        addSampleProduct("0004", "Diapers", 50, BigDecimal.valueOf(16.99));
        addSampleProduct("0005", "Water", 100, BigDecimal.valueOf(3.99));
        addSampleProduct("0000", "Nonexistent Towels", 0, BigDecimal.valueOf(3.99));

        //add Perishables
        addSamplePerishable("0006", "Roma Tomatoes", 150, BigDecimal.valueOf(2.99), LocalDate.of(2025, 7, 25));
        addSamplePerishable("0007", "Apples", 200, BigDecimal.valueOf(1.99), LocalDate.of(2025, 7, 25));
        addSamplePerishable("0008", "Milk", 150, BigDecimal.valueOf(4.75), LocalDate.of(2025, 7, 25));
        addSamplePerishable("0009", "Potatoes", 100, BigDecimal.valueOf(5.99), LocalDate.of(2025, 8, 1));
        addSamplePerishable("0010", "Avocados", 100, BigDecimal.valueOf(8.99), LocalDate.of(2025, 7, 18));
        addSamplePerishable("9999", "Expired Yogurt", 10, BigDecimal.valueOf(2.99), LocalDate.of(2024, 7,11));
    }


    private void addSampleProduct(String productID, String productName, int quantity, BigDecimal price) {
        Product product = new Product(productID, productName);
        InventoryItem item = new InventoryItem(product, quantity, price);
        inventory.put(productID, item);
    }

    private void addSamplePerishable(String productID, String productName, int quantity, BigDecimal price,
                                     LocalDate expirationDate) {
        PerishableProduct product = new PerishableProduct(productID, productName, expirationDate);
        InventoryItem item = new InventoryItem(product, quantity, price);
        inventory.put(productID, item);
    }

    @Override
    public List<InventoryItem> getAll() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public void add(InventoryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("No Item detected");
        }
        inventory.put(item.getProduct().getProductID(), item);
    }

    @Override
    public void update(InventoryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("No Item detected");
        }
        String productID = item.getProduct().getProductID();
        if (!inventory.containsKey(productID)) {
            throw new IllegalArgumentException("ProductID " + productID + " not found. please try again.");
        }
        inventory.put(productID, item);

    }

    @Override
    public void delete(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("No item detected.");
        }
        inventory.remove(productID);
    }

    @Override
    public InventoryItem getByID(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("No Item detected");
        }
        return inventory.get(productID);
    }

    //one time use method to load the data into a csv file
    public void exportToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (InventoryItem item : inventory.values()) {
                String productID = item.getProduct().getProductID();
                String productName = item.getProduct().getProductName();
                int quantity = item.getQuantity();
                BigDecimal price = item.getPrice();
                boolean isPerishable = item.getProduct() instanceof PerishableProduct;
                String expirationDate = isPerishable ? ((PerishableProduct) item.getProduct()).getExpirationDate().toString() : "";

                writer.printf("%s,%s,%d,%s,%s,%s%n",
                        productID,
                        productName,
                        quantity,
                        price,
                        isPerishable,
                        expirationDate);
            }

            System.out.println("Inventory exported to " + filename);
        } catch (IOException e) {
            System.out.println("Failed to export inventory to CSV: " + e.getMessage());
        }
    }
}

