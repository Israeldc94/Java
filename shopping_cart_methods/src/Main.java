
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create arrays to contain addresses and sizes
        String[] addresses = { "123 Main St", "456 Main St", "789 Main St" };
                String[] sizes = { "small", "medium", "large" };
        int addressIndex = 0;
        int sizeIndex = 0;

        System.out.println("Welcome to the shopping cart app!");
        java.util.Scanner console = new
                java.util.Scanner(System.in);
        double productPrice = 4.99;
        int productQuantity = 78;
        double totalCost = productPrice * productQuantity;

        double taxRate = .07;
        double hundredDollarDiscount = .05;
        double fiveHundredDollarDiscount = .10;

        double standardShipping = 2.00;
        double overnightShipping = 10.00;
        double twoDayShipping = 5.00;
        boolean isConfirmed = false;


        System.out.printf("Product Price: $%.2f", productPrice);
        System.out.println("\nProduct Quantity: " + productQuantity);
        System.out.printf("\nTotal Cost: $%.2f", totalCost);
        double shippingCost = 0;
        String shipping = null;
        String promoCode = null;
        while (!isConfirmed) {
// Prompt for tax exempt
           String taxExempt = promptUserForString("\n Are you tax exempt?");
// Prompt for shipping
            shipping = promptUserForString("Shipping? (standard / overnight / two-day)");

//Prompt for address
            displayChoices(addresses);

            addressIndex = promptUserForInt("Enter your shipping address: ");



// Prompt for Size
            displayChoices(sizes);
            sizeIndex = promptUserForInt("Size?");


// Prompt for promo code
            promoCode = promptUserForString("Promo code for free shipping?");

// Print details
            // Print details
            System.out.println("\nDetails:");
            System.out.println("Address: " + addresses[addressIndex - 1]);
            System.out.println("Size: " + sizes[sizeIndex - 1]);

            // Apply tax, if necessary
            if (taxExempt.equals("n")) {
                totalCost = totalCost + (totalCost * taxRate);
            }
            System.out.printf("Total w/ tax: $%.2f", totalCost);
            System.out.println("\nTax-exempt: " + taxExempt);
            // Apply discount
            if (totalCost >= 500) {
                totalCost = totalCost - (totalCost *
                        fiveHundredDollarDiscount);
            } else if (totalCost >= 100) {
                totalCost = totalCost - (totalCost * hundredDollarDiscount);
            }
            shippingCost = 0.00;
            switch (shipping) {
                case "standard":
                    shippingCost = standardShipping;
                    if (promoCode.equals("FREE")) {
                        shippingCost = 0;
                    }
                    break;
                case "overnight":
                    shippingCost = overnightShipping;
                    break;
                case "twoday":
                    shippingCost = twoDayShipping;
                    break;
                default:
// bad shipping type
                    System.out.println("Invalid shipping type");
            }
            System.out.println("Confirm Order y/n ");
            isConfirmed = "y".equals(console.nextLine());
        }
        totalCost += shippingCost;
        System.out.printf("Shipping Cost: $%.2f", shippingCost);
        System.out.printf("\nFinal Total: $%.2f", totalCost);
        System.out.printf("\nTotal after discount: $%.2f", totalCost);
        System.out.println("\nShipping: " + shipping);
        System.out.println("Promo code: " + promoCode);
        System.out.println("Bye");
    }
    private static void displayChoices(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + ": " + choices[i]);
        }
    }
    private static String promptUserForString(String prompt) {
        java.util.Scanner console = new
                java.util.Scanner(System.in);
        System.out.println(prompt);
        return console.nextLine();
    }
    private static int promptUserForInt(String prompt) {
        java.util.Scanner console = new
                java.util.Scanner(System.in);
        System.out.println(prompt);
        return Integer.parseInt(console.nextLine());
    }



}