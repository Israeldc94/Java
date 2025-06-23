package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.Mainapp.IO;
import org.example.Mainapp.Item;

import java.util.HashMap;

public class DisplayCartCommand implements CartCommand{

    IO io = new IO();

    @Override
    public String execute(CartService cart) {
     HashMap<Item, Integer> cartList = cart.getCart();

     if (cartList.isEmpty()) {
         return "Your cart is empty.";
     }else{

         String result = "\nYour cart:\n";
         for (Item item: cartList.keySet()) {
             int quantity = cartList.get(item);
             result += String.format("%s $%.2f x %d\n",item.getName(),item.getPrice(), quantity);
         }
         double total = cart.getTotal();
         result += String.format("Subtotal: $%.2f", total);

         return result;
     }



    }


}
