package com.example.inventory_capstone.view.admin_commands;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.Command;
import com.example.inventory_capstone.view.InventoryConsoleIO;


public class ViewItemCommand implements Command {
    private final InventoryService service;
    private final InventoryConsoleIO io;


    public ViewItemCommand(InventoryService service, InventoryConsoleIO io) {
        this.service = service;
        this.io = io;
    }



    @Override
    public void execute() {
        ViewAvailableInventoryCommand viewInventory = new ViewAvailableInventoryCommand(service, io);
        viewInventory.execute();

        InventoryItem item = service.getItem(io.getNonEmptyString("Enter a product ID: "));

    }
}
