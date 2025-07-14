package com.example.inventory_capstone.view;

import com.example.inventory_capstone.model.InventoryItem;
import com.example.inventory_capstone.model.PerishableProduct;
import com.example.inventory_capstone.model.Product;
import com.example.inventory_capstone.repository.InventoryRepository;
import com.example.inventory_capstone.service.InventoryService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
public class InventoryConsoleIO extends ConsoleIO {

    private Scanner scanner;


    public InventoryConsoleIO() {
        this.scanner = new Scanner(System.in);
    }


    public void displayStart() {
        print("==================================");
        print("Welcome to Inventory Management!");
        print("==================================");
        print("");
    }

    public void displayEnd() {
        print("");
        print("Thank you for using Inventory Management");
        print("Have a nice day!");
        print("");

    }

    @Override
    public void printMenu() {
        print("\n1. View available inventory" +
                "\n2. View all inventory " +
                "\n3. View item" +
                "\n4. Update/add Item " +
                "\n5. Remove item " +
                "\n6. Exit");

    }
    public void displayInventory(List<InventoryItem> items) {
        print("═══════════════════════════════════════════════════════════════════════════");
        print("                        Inventory items                                    ");
        print("═══════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-10s %-20s %-10s %-10s %-15s%n", "ID", "Name", "Quantity", "Price", "Expiration Date");
        print("---------------------------------------------------------------------------------------------");

        for (InventoryItem item : items) {
            String productID = item.getProduct().getProductID();
            String productName = item.getProduct().getProductName();
            int quantity = item.getQuantity();
            BigDecimal price = item.getPrice();
            String expirationDate = "N/A";

            if (item.getProduct() instanceof PerishableProduct pp) {
                expirationDate = pp.getExpirationDate().toString();
            }

            System.out.printf("%-10s %-20s %-10d %-10.2f %-15s%n",
                    productID, productName, quantity, price, expirationDate);
            print("═════════════════════════════════════════════════════════════════════════════════════════════");
        }
    }

        public void displayCustomerInventory(List<InventoryItem> items) {
            print("═════════════════════════════════════════════════════════════════════════════════════════════");
            print("                              Customer-Available Inventory                                   ");
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

    public String authenticateUser() {
       String password = getNonEmptyString("Please enter admin password");
       return password;
    }

    public LocalDate getDate(String prompt) {
        while (true) {
            try {
                String input = getNonEmptyString(prompt);
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                print("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
    public BigDecimal getBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        while (true) {
            try {
                String input = getNonEmptyString(prompt);
                BigDecimal value = new BigDecimal(input);

                if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
                    print(String.format("Please enter a value between %s and %s.", min, max));
                } else {
                    return value.setScale(2, BigDecimal.ROUND_HALF_UP);
                }

            } catch (NumberFormatException e) {
                print("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

}



