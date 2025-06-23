package org.example.commands;

import org.example.Mainapp.CartService;
import org.example.commands.DisplayCartCommand;

public class CheckoutCartCommand implements CartCommand{
    DisplayCartCommand displayCart = new DisplayCartCommand();

    @Override
    public String execute(CartService cart) {
    String cartList = displayCart.execute(cart);
    double total = cart.getTotal();
    double salesTax = 0.08;
    return String.format( "%s \n Your total is: $%.2f \n Thank you for shopping at Wally-World!", cartList,(total + (total * salesTax)));
    }
}
