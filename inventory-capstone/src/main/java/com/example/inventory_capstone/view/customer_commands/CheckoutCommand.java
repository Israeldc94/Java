package com.example.inventory_capstone.view.customer_commands;

import com.example.inventory_capstone.model.Result;
import com.example.inventory_capstone.service.CartService;
import com.example.inventory_capstone.view.CartConsoleIO;
import com.example.inventory_capstone.view.Command;


public class CheckoutCommand implements Command {
    private final CartService service;
    private final CartConsoleIO io;

    public CheckoutCommand(CartService service, CartConsoleIO io) {
        this.service = service;
        this.io = io;
    }

    @Override
    public void execute() {
        io.displayCart(service.getItemsInCart());
        boolean confirmation = io.getConfirmation("Are you sure you would like to checkout? (y/n): ");
        if (!confirmation) {
            return;
        }
        Result result = service.checkout();
        io.print(result.getMessage());

    }
}
