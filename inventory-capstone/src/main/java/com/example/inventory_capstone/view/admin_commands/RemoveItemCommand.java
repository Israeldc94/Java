package com.example.inventory_capstone.view.admin_commands;

import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.Command;
import com.example.inventory_capstone.view.InventoryConsoleIO;

public class RemoveItemCommand implements Command {
    private final InventoryService service;
    private final InventoryConsoleIO io;

    public RemoveItemCommand(InventoryService service, InventoryConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        ViewAvailableInventoryCommand viewInventory = new ViewAvailableInventoryCommand(service, io);
        viewInventory.execute();
        String productID = io.getNonEmptyString("Enter a product ID to remove a product");
       boolean confirmation = io.getConfirmation("Are you sure you would like to remove this item? (y/n)");
        if (!confirmation) {
            return;
        }
        service.removeItem(productID);

    }
}
