import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        System.out.printf("Product Price: $%.2f", productPrice);
        System.out.println("\nProduct Quantity: " + productQuantity);
        System.out.printf("\nTotal Cost: $%.2f", totalCost);
// Prompt for tax exempt
        System.out.println("\nAre you tax-exempt? (y/n)");
        String taxExempt = console.nextLine();
// Prompt for shipping
        System.out.println("Shipping? (standard / overnight / two-day)");
        String shipping = console.nextLine();
// Prompt for promo code
        System.out.println("Promo code for free shipping?");
                String promoCode = console.nextLine();
// Print details
        System.out.println("\nDetails:");
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
        double shippingCost = 0.00;
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
        totalCost += shippingCost;
        System.out.printf("Shipping Cost: $%.2f", shippingCost);
        System.out.printf("\nFinal Total: $%.2f", totalCost);
        System.out.printf("\nTotal after discount: $%.2f", totalCost);
        System.out.println("\nShipping: " + shipping);
        System.out.println("Promo code: " + promoCode);
        System.out.println("Bye");

    }
    }