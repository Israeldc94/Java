package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;

public class AddAnItemCommand implements ItemCommand {


    @Override
    public void execute(CartService cart, Item item, int quantity) {
        cart.addItem(item, quantity);
    }
}
