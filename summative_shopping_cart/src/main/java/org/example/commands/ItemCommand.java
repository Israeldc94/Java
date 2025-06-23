package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;

public interface ItemCommand {
    public void execute(CartService cart, Item item, int quantity);
}
