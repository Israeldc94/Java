package com.example.inventory_capstone.view.admin_commands;

import com.example.inventory_capstone.service.InventoryService;
import com.example.inventory_capstone.view.Command;
import com.example.inventory_capstone.view.InventoryConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ViewAvailableInventoryCommand implements Command {
    private final InventoryService service;
    private final InventoryConsoleIO io;

    public ViewAvailableInventoryCommand(InventoryService service, InventoryConsoleIO io) {
        this.service = service;
        this.io = io;
    }


    @Override
    public void execute() {
        io.displayCustomerInventory(service.getCustomerAvailableInventory());

    }
}
