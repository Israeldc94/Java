package com.example.inventory_capstone.view.admin_commands;

import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.Command;
import com.example.inventory_capstone.view.InventoryConsoleIO;

public class ViewAllInventoryCommand implements Command {
    private final InventoryService service;
    private final InventoryConsoleIO io;

    public ViewAllInventoryCommand(InventoryService service, InventoryConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        io.displayInventory(service.getAllItems());
    }
}
