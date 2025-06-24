package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.commands.DisplayCartCommand;
import org.example.Mainapp.IO;



public class CheckoutCartCommand implements Command{
CartService cart = new CartService();
IO io = new IO();
DisplayCartCommand displayCart = new DisplayCartCommand(cart, io);

    private CartService cartService;
    private IO iO;
    private DisplayCartCommand displaycart;


    public CheckoutCartCommand(CartService cart, IO io, DisplayCartCommand displayCart) {
        this.cart = cart;
        this.io = io;
        this.displayCart = displayCart;
    }



    @Override
    public void execute() {
        displayCart.execute();
        double total = cart.getTotal();
        double salesTax = 0.08;
        io.print(String.format( "Your total is: $%.2f \n Thank you for shopping at Wally-World!", (total + (total * salesTax))));
    }
}
