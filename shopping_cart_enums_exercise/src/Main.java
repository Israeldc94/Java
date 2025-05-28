//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    enum OrderStatus{
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    enum ShippingStatus{
        STANDARD,
        TWO_DAY,
        OVERNIGHT
    }
    public static void main(String[] args) {
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



    }
}