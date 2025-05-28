//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    int productID = 1;
    int productCategory = 2;
    float productCost = 2.56f;
    float productPrice = 4.99f;
    int productQuantity = 78;
    double totalCost = (productCost * productQuantity);
    float totalProfit = (productPrice * productQuantity);
    float trueProfit = (totalProfit - (productCost *productQuantity));

        System.out.println("Total product cost: " + "$" + totalCost);
        double profitPerProduct = productPrice - productCost;
        System.out.println("Profit per product: $" + profitPerProduct);
        System.out.println("Profit Margin: " + ((trueProfit / totalProfit) * 100) + "%");
        System.out.println("Profit: $" + trueProfit);
        System.out.println("Bye");


        }
    }
