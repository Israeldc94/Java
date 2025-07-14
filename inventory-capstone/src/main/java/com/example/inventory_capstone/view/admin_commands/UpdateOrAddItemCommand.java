package com.example.inventory_capstone.view.admin_commands;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.PerishableProduct;
import com.example.inventory_capstone.model.Product;
import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.Command;
import com.example.inventory_capstone.view.InventoryConsoleIO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateOrAddItemCommand implements Command {
    private final InventoryService service;
    private final InventoryConsoleIO io;

    public UpdateOrAddItemCommand(InventoryService service, InventoryConsoleIO io) {
        this.service = service;
        this.io = io;

    }


    @Override
    public void execute() {
       String productID = io.getNonEmptyString("Enter a product ID: ");
       String productName = io.getNonEmptyString("Enter a product name: ");
        int quantity = io.getInt("Enter the quantity", 1, 10000);
        BigDecimal price = io.getBigDecimal("Enter the price: ", BigDecimal.ZERO, new BigDecimal(10000));
        boolean isPerishable = io.getConfirmation("Is this item perishable? (y/n): ");

        Product product;

        if (isPerishable) {
            LocalDate expirationDate = io.getDate("Enter the expiration date (YYYY-MM-DD) ");
            product = new PerishableProduct(productID, productName,expirationDate);
        } else {
            product = new Product(productID,productName);
        }

        InventoryItem item = new InventoryItem(product, quantity, price);
        service.updateOrAddItem(item);

        io.print("successfully updated inventory");
    }
}
