package com.example.inventory_capstone.view.customer_commands;

import com.example.inventory_capstone.model.Result;
import com.example.inventory_capstone.service.CartService;
import com.example.inventory_capstone.view.CartConsoleIO;
import com.example.inventory_capstone.view.Command;

public class RemoveItemCommand implements Command {
    private final CartService service;
    private final CartConsoleIO io;

    public RemoveItemCommand(CartService service, CartConsoleIO io) {
        this.service = service;
        this.io = io;
    }



    @Override
    public void execute() {
        io.displayCart(service.getItemsInCart());
        String productID = io.getNonEmptyString("Enter the product ID to remove: ");
        int quantity = io.getInt("How many would you like to remove? ",1, 10000);
        boolean confirmation = io.getConfirmation("Are you sure you would like to remove item(s) from your cart?");
        if (!confirmation) {
            return;
        }
        Result result = service.removeFromCart(productID,quantity);
        io.print(result.getMessage());
    }
}
