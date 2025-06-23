package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;

public class RemoveItemCommand implements ItemCommand {

    @Override
    public void execute(CartService cart, Item item, int quantity) {
        if (!(item == null)) {
            cart.removeItem(item, quantity);
        } else {
            System.out.println("Item not found");
        }
    }
}
