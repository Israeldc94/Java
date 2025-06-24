package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.Item;
import org.example.Mainapp.IO;
public class RemoveItemCommand implements Command {
private CartService cart;
private IO io;

public RemoveItemCommand(CartService cart, IO io){
    this.cart = cart;
    this.io = io;
}

    @Override
    public void execute() {
        if (!cart.getCart().isEmpty()) {
            String itemName = io.getNonEmptyString("Enter the item you'd like to remove: ");
            Item item = cart.getItemByName(itemName);
            if  (item == null) {
                io.print("Item not found in cart");
            }
            int quantity = io.getInt("How many would you like to remove?: ", 1, 1000);
            cart.removeItem(item, quantity);;
            io.print("item(s) have been removed.");
        } else {
            io.print("Your cart is empty");
        }



    }
}
