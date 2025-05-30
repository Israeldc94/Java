import java.util.Scanner;

public class Main {
    enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    enum ShippingStatus {
        STANDARD,
        TWO_DAY,
        OVERNIGHT
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Are you tax -  exempt? (y/n)");
        String taxExempt = console.nextLine();

        System.out.println("Shipping? (y/n) ");
        String shipping = console.nextLine();

        System.out.println("Order quantity? ");
        int orderQuantity = Integer.parseInt(console.nextLine());

        System.out.println("Promo code for free shipping? ");
        String promoCode = console.nextLine();

        System.out.println("\nDetails: ");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping: " + shipping);
        System.out.println("Order quantity: " + orderQuantity);
        System.out.println("Promo code: " + promoCode);

        int productID = 1;
        int productCategory = 2;
        float productCost = 2.56f;
        float productPrice = 4.99f;
        int productQuantity = 78;
        double totalCost = (productCost * productQuantity);
        float totalProfit = (productPrice * productQuantity);
        float trueProfit = (totalProfit - (productCost * productQuantity));
        double profitPerProduct = productPrice - productCost;
        OrderStatus orderStatus = OrderStatus.PROCESSING;
        ShippingStatus shipStatus = ShippingStatus.TWO_DAY;

        String businessName = "My business";
        String businessContactInfo = "123-456-7890";
        String productDescription = "My product description";

        System.out.println("Total product cost: " + "$" + totalCost);
        System.out.println("Profit per product: $" + profitPerProduct);
        System.out.println("Profit Margin: " + ((trueProfit / totalProfit) * 100) + "%");
        System.out.println("Profit: $" + trueProfit);
        System.out.println("Bye");

        System.out.println(ShippingStatus.STANDARD);
        System.out.println(ShippingStatus.TWO_DAY);
        System.out.println(ShippingStatus.OVERNIGHT);
        System.out.println("Order Status" + orderStatus);

        System.out.println(OrderStatus.PENDING);
        System.out.println(OrderStatus.PROCESSING);
        System.out.println(OrderStatus.SHIPPED);
        System.out.println(OrderStatus.DELIVERED);
        System.out.println("Ship Status: " + shipStatus);

        System.out.println("Business name: " + businessName);
        System.out.println("Business contact info: " + businessContactInfo);
        System.out.println("Product description: " + productDescription);
    }
}