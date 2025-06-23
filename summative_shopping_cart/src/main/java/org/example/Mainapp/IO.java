package org.example.Mainapp;
import java.util.Scanner;
import java.util.ArrayList;

public class IO {
Scanner io = new Scanner(System.in);
public ArrayList<String> mainMenu = new ArrayList<>();

public IO(){
    mainMenu.add("Display Cart");
    mainMenu.add("Remove an Item");
    mainMenu.add("Add an Item");
    mainMenu.add("Checkout");
    mainMenu.add("Exit");
}
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

        public void print(String message) {
            System.out.println(message);
        }

        public void printMenu(){
            print("\n===Main Menu===");
            for (int i = 0; i < mainMenu.size(); i++) {
                print((i + 1) + ". " + mainMenu.get(i));
            }
        }

    public double getDouble(String prompt) {
        double price = 0;
        while (true) {
            print(prompt);
            String resultStr = io.nextLine();
            try {
                price = Double.parseDouble(resultStr);
                return price;
            } catch (NumberFormatException | NullPointerException ex) {
                print("\nInvalid input. Please enter a valid price.");
            }
        }
    }







}
