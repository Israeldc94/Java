package com.example.inventory_capstone.view.customer_commands;

import com.example.inventory_capstone.model.Result;
import com.example.inventory_capstone.service.CartService;
import com.example.inventory_capstone.view.CartConsoleIO;
import com.example.inventory_capstone.view.Command;

public class UpdateOrAddItemCommand implements Command {
    private final CartService service;
    private final CartConsoleIO io;

    public UpdateOrAddItemCommand(CartService service, CartConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        ViewAvailableInventoryCommand viewAvailableInventory = new ViewAvailableInventoryCommand(service, io);
        viewAvailableInventory.execute();

        String productID = io.getNonEmptyString("Enter a productID");
        int quantity = io.getInt("How many would you like to add?", 1, 10000);
        Result result = service.addToCart(productID, quantity);
        io.print(result.getMessage());
    }
}
