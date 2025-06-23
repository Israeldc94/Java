package org.example.commands;

import org.example.Mainapp.CartService;

public interface CartCommand {
    public String execute(CartService cart);
}
