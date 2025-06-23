package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;

public class RemoveItemCommand implements ItemCommand {

    @Override
    public void execute(CartService cart, Item item, int quantity) {
            cart.removeItem(item, quantity);

    }
}
