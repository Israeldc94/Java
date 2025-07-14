package com.example.inventory_capstone.view.customer_commands;

import com.example.inventory_capstone.service.CartService;
import com.example.inventory_capstone.view.CartConsoleIO;
import com.example.inventory_capstone.view.Command;

public class ViewAvailableInventoryCommand implements Command {
    private final CartService service;
    private final CartConsoleIO io;

    public ViewAvailableInventoryCommand(CartService service,CartConsoleIO io){
        this.service = service;
        this.io = io;
    }
    @Override
    public void execute() {
        io.displayCustomerInventory(service.getAvailableInventory());

    }
}
