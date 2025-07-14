package com.example.inventory_capstone.view;

import com.example.inventory_capstone.model.CartItem;
import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.PerishableProduct;
import com.example.inventory_capstone.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CartConsoleIO extends ConsoleIO {

    private final Scanner scanner = new Scanner(System.in);

    public void displayStart() {
        print("==================================");
        print("       Welcome to Our Store!      ");
        print("==================================");
        print("");
    }

    public void displayEnd() {
        print("");
        print("Thanks for shopping with us!");
        print("Come again soon!");
        print("");
    }

    @Override
    public void printMenu() {
        print("\n1. View available inventory" +
                "\n2. View item details" +
                "\n3. View Cart" +
                "\n4. Add item to cart" +
                "\n5. Remove item" +
                "\n6. Checkout" +
                "\n7. Exit");
    }

    public void displayCustomerInventory(List<InventoryItem> items) {
        print("═════════════════════════════════════════════════════════════════════════════════════════════");
        print("                              Items Available to Purchase                                   ");
        print("═════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-10s %-20s %-10s %-10s %-15s%n", "ID", "Name", "Quantity", "Price", "Expiration Date");
        print("---------------------------------------------------------------------------------------------");

        for (InventoryItem item : items) {
            Product product = item.getProduct();

            if (product instanceof PerishableProduct pp) {
                System.out.printf("%-10s %-20s %-10d %-10.2f %-15s%n",
                        pp.getProductID(),
                        pp.getProductName(),
                        item.getQuantity(),
                        item.getPrice(),
                        pp.getExpirationDate());
            } else {
                System.out.printf("%-10s %-20s %-10d %-10.2f %-15s%n",
                        product.getProductID(),
                        product.getProductName(),
                        item.getQuantity(),
                        item.getPrice(),
                        "N/A");
            }
        }

        print("═════════════════════════════════════════════════════════════════════════════════════════════");
    }

    // Removed getQuantity in favor of using getInt from ConsoleIO

    public void displayCart(ArrayList<CartItem> cartItems) {
        if (cartItems.isEmpty()) {
            print("Your cart is currently empty.");
            return;
        }

        print("═════════════════════════════════════════════════════════════════════════════════════════════");
        print("                                      Your Cart                                              ");
        print("═════════════════════════════════════════════════════════════════════════════════════════════");

        BigDecimal subtotal = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            subtotal = subtotal.add(item.getExtendedPrice());

            System.out.printf("%s - %s | Quantity: %d | Price: $%.2f%n",
                    item.getProduct().getProductID(),
                    item.getProduct().getProductName(),
                    item.getQuantity(),
                    item.getPrice());
        }
        print("═════════════════════════════════════════════════════════════════════════════════════════════");
        print(String.format("Subtotal: $%.2f", subtotal));
    }

    public boolean getConfirmation(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim().toLowerCase();

        while (!input.equals("y") && !input.equals("n") &&
                !input.equals("yes") && !input.equals("no")) {
            print("Please enter 'y' for yes or 'n' for no.");
            System.out.print(prompt);
            input = scanner.nextLine().trim().toLowerCase();
        }

        return input.equals("y") || input.equals("yes");
    }

    public void displaySingleItem(InventoryItem item) {
        if (item == null) {
            print("Item not found.");
            return;
        }

        Product product = item.getProduct();
        String expiration = "N/A";

        if (product instanceof PerishableProduct pp) {
            expiration = pp.getExpirationDate().toString();
        }

        print("═══════════════════════════════════════════════════════");
        print("                     Item Details                      ");
        print("═══════════════════════════════════════════════════════");
        print(String.format("ID:              %s%n", product.getProductID()));
        print(String.format("Name:            %s%n", product.getProductName()));
        print(String.format("Quantity:        %d%n", item.getQuantity()));
        print(String.format("Price:           $%.2f%n", item.getPrice()));
        print(String.format("Expiration Date: %s%n", expiration));
        print("═══════════════════════════════════════════════════════");
    }
}
