package org.example.Mainapp;

import org.example.commands.*;

public class App {
    static CartService cart = new CartService();
    static IO io = new IO();
    static AddAnItemCommand addItem = new AddAnItemCommand();
    static RemoveItemCommand removeItem = new RemoveItemCommand();
    static DisplayCartCommand displayCart = new DisplayCartCommand();
    static CheckoutCartCommand checkout = new CheckoutCartCommand();
    static ItemFactory itemFactory = new ItemFactory();

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
                    io.print(displayCart.execute(cart));
                    break;

                case REMOVE_AN_ITEM:
                    if (!cart.getCart().isEmpty()) {
                        String itemName = io.getNonEmptyString("Enter the item you'd like to remove: ");
                        Item item = cart.getItemByName(itemName);
                        if  (item == null) {
                            io.print("Item not found in cart");
                            break;
                        }
                        int quantity = io.getInt("How many would you like to remove?: ", 1, 1000);
                        removeItem.execute(cart, item, quantity);
                        io.print("item(s) have been removed.");
                        break;
                    } else {
                        io.print("Your cart is empty");
                        break;
                    }


                case ADD_AN_ITEM:
                    String itemName = io.getNonEmptyString("Enter the item you'd like to add: ");
                    double price = io.getDouble("Enter the price of the item: ");
                    int quantity = io.getInt("How many would you like to add?: ", 1, 1000);
                    Item item = itemFactory.createItem(itemName, price);
                    addItem.execute(cart, item, quantity);
                    io.print("item(s) have been added");
                    break;


                case CHECKOUT:
                    io.print(checkout.execute(cart));
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
