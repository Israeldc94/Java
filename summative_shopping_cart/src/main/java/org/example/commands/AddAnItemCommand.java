package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;
import org.example.Mainapp.IO;
import org.example.Mainapp.ItemFactory;

public class AddAnItemCommand implements Command {
 private CartService cart;
 private IO io;
 private ItemFactory itemFactory;

public AddAnItemCommand(CartService cart, IO io, ItemFactory itemFactory){
    this.cart = cart;
    this.io = io;
    this.itemFactory = itemFactory;
}

    @Override
    public void execute() {
        String itemName = io.getNonEmptyString("Enter the item you'd like to add: ");
        double price = io.getDouble("Enter the price of the item: ");
        int quantity = io.getInt("How many would you like to add?: ", 1, 1000);
        Item item = itemFactory.createItem(itemName, price);
        cart.addItem(item, quantity);
        io.print("item(s) have been added");
    }
}
