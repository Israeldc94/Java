package com.example.capstone_inventory.repository;

import com.example.capstone_inventory.model.InventoryItem;
import com.example.capstone_inventory.model.PerishableProduct;
import com.example.capstone_inventory.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

public class CsvInventoryRepository implements InventoryRepository {

    private final HashMap<String, InventoryItem> inventory = new HashMap<>();
    @Value("${inventory.csv.filepath:data/inventory.csv}")
    private String filename;

    public CsvInventoryRepository(String filename) {
        this.filename = filename;
        loadFromFile();
    }

    @PostConstruct
    public void initialize() {
        loadFromFile();
    }

    @Override
    public List<InventoryItem> getAll() {
        return new ArrayList<>(inventory.values());
    }

    public List<InventoryItem> getInStock() {
        return inventory.values().stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }


    @Override
    public void add(InventoryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item not detected");
        }
        inventory.put(item.getProduct().getProductID(), item);
        saveToFile();

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
        saveToFile();
    }


    @Override
    public void delete(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("No item detected.");
        }
        inventory.remove(productID);
        saveToFile();
    }


    @Override
    public InventoryItem getByID(String productID) {
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("No Item detected");
        }
        return inventory.get(productID);
    }


    private void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] sections = line.split(",", -1);

                String productID = sections[0].trim();
                String productName = sections[1].trim();
                int quantity = Integer.parseInt(sections[2].trim());
                BigDecimal price = new BigDecimal(sections[3].trim());
                boolean isPerishable = sections.length > 4 && Boolean.parseBoolean(sections[4].trim());

                Product product;

                if (isPerishable && sections.length > 5 && !sections[5].trim().isEmpty()) {
                    LocalDate expirationDate = LocalDate.parse(sections[5].trim());
                    product = new PerishableProduct(productID, productName, expirationDate);
                } else {
                    product = new Product(productID, productName);
                }

                InventoryItem item = new InventoryItem(product, quantity, price);
                inventory.put(productID, item);
            }

        } catch (IOException | NumberFormatException | DateTimeException e) {
            System.out.println("error reading inventory file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        File file = new File(filename);

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {

            for (InventoryItem item : inventory.values()) {
                Product product = item.getProduct();
                String productID = product.getProductID();
                String productName = product.getProductName();
                int quantity = item.getQuantity();
                BigDecimal price = item.getPrice();
                boolean isPerishable = product instanceof PerishableProduct;
                String expirationDate = "";

                if (isPerishable) {
                    expirationDate = ((PerishableProduct) product).getExpirationDate().toString();
                }
                writer.printf("%s,%s,%d,%s,%s,%s%n",
                        productID,
                        productName,
                        quantity,
                        price,
                        isPerishable,
                        expirationDate);
            }
        } catch (IOException e) {
            System.out.println("Can't write inventory to file.");
        }

    }
}


