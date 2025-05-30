import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the order form!");

        System.out.println("What is your name? ");
        String name = console.nextLine();

        System.out.println("Hello, " +name +"!" + "Let's get started with your order.");

        System.out.println("What product would you like to purchase? ");
        String product = console.nextLine();

        System.out.println("How many would you like? ");
        String quantityStr = console.nextLine();
        int quantity = Integer.parseInt(quantityStr);

        System.out.println("What is the unit price? ");
        String priceStr = console.nextLine();
        double price = Double.parseDouble(priceStr);

        double subTotal = (quantity * price);
        double tax = (.07 * subTotal);
        double total = (subTotal + tax);
        System.out.println("Order Summary");
        System.out.println("------------------------------");
        System.out.println("Item:            " + product);
        System.out.println("Quantity:        " + quantity);
        System.out.println("Unit Price:      " + "$" + price);
        System.out.println("------------------------------");
        System.out.println("Subtotal:        " + "$" + subTotal);
        String taxBreakdown = String.format("Tax (7%%):        " + "$" + "%.2f", tax);
        System.out.println(taxBreakdown);
        System.out.printf("Grand Total:     " + "$" + "%.2f", total);
        System.out.println("\n------------------------------");
        System.out.println("Thank you for your order, " + name);




    }
}