package org.example.Mainapp;

import org.example.commands.*;

public class App {
    static CartService cart = new CartService();
    static IO io = new IO();
    static ItemFactory itemFactory = new ItemFactory();
    static AddAnItemCommand addItem = new AddAnItemCommand(cart, io, itemFactory);
    static RemoveItemCommand removeItem = new RemoveItemCommand(cart, io);
    static DisplayCartCommand displayCart = new DisplayCartCommand(cart, io);
    static CheckoutCartCommand checkout = new CheckoutCartCommand(cart, io, displayCart);


    private static final int DISPLAY_CART = 1;
    private static final int REMOVE_AN_ITEM = 2;
    private static final int ADD_AN_ITEM = 3;
    private static final int CHECKOUT = 4;
    private static final int EXIT = 5;

    public static void main(String[] args) {
        boolean keepRunning = true;

        while (keepRunning) {
            io.printMenu();
            int choice = io.getInt("Select an option: ", 1, 5);

            switch (choice) {

                case DISPLAY_CART:
                    displayCart.execute();
                    break;

                case REMOVE_AN_ITEM:
                        removeItem.execute();
                        break;

                case ADD_AN_ITEM:
                    addItem.execute();
                    break;


                case CHECKOUT:
                    checkout.execute();
                    cart.clearCart();
                    break;

                case EXIT:
                    System.exit(0);
                    keepRunning = false;
                    break;

                default:
                    System.exit(0);
                    break;
            }
        }

    }
}
