package com.example.inventory_capstone.view;

import java.util.Scanner;

public class ConsoleIO implements TextIO {
    private final Scanner io = new Scanner(System.in);

    @Override
    public void print(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public String getNonEmptyString(String prompt) {
        String input;
        print(prompt);
        input = io.nextLine();
        if (input.isEmpty()) {
            print("\nInvalid input");
            return input;
        } else {
            return input;
        }
    }


    @Override
    public int getInt(String prompt, int min, int max) {
        int num = 0;
        while (true) {
            print(prompt);
            String resultStr = io.nextLine();
            try {
                num = Integer.parseInt(resultStr);
                if (num < min || num > max) {
                    print("\nThat option does not exist");
                } else {
                    return num;
                }
            } catch (NumberFormatException | NullPointerException ex) {
                print("\nInvalid input. Please enter a valid number.");
            }
        }
    }

    @Override
    public void printMenu() {
        print("\n1. View inventory \n2. View Cart \n3. Add Item \n4. Remove Item \n5. Checkout \n6.Exit");
    }
}

