
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to the line item app!");

        System.out.print("What is your name? ");

        String name = console.nextLine();

        System.out.print("What product would you like to purchase? ");

        String product = console.nextLine();

        System.out.print("How many would you like? ");

        String quantityStr = console.nextLine();

        int quantity = Integer.parseInt(quantityStr);

        System.out.print("What is the product cost? ");

        String costStr = console.nextLine();

        double cost = Double.parseDouble(costStr);

        //print summary

        System.out.println("Line Item Summary for: " +name);

        System.out.println("Item:               Qty\t Unit price\tLine Total (incl. tax)");

        System.out.println("====================\t==========\t=========");

        String lineItemSummary = String.format("%s\t%s\t$%s\t\t$%.2f", product, quantity,
                cost, (quantity * cost * 1.75));

        System.out.println(lineItemSummary);
    }
}