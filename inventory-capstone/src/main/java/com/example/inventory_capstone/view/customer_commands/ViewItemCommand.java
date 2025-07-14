package com.example.inventory_capstone.view.customer_commands;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.service.CartService;
import com.example.inventory_capstone.view.CartConsoleIO;
import com.example.inventory_capstone.view.Command;
import com.example.inventory_capstone.view.customer_commands.ViewAvailableInventoryCommand;

public class ViewItemCommand implements Command {
    private final CartService service;
    private final CartConsoleIO io;

    public ViewItemCommand(CartService service, CartConsoleIO io) {
        this.service = service;
        this.io = io;
    }


    @Override
    public void execute() {
        ViewAvailableInventoryCommand viewAvailableInventory = new ViewAvailableInventoryCommand(service, io);
        viewAvailableInventory.execute();
       InventoryItem item = service.getItem(io.getNonEmptyString("Enter Product ID: "));
       io.displaySingleItem(item);


    }
}
