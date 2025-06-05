import java.util.Scanner;

public class calculations_and_utils {


    public calculations_and_utils(){
        Scanner console = new Scanner(System.in);
    }

    public static double getDepositAmt(){
    String paycheckStr = promptString("Enter paycheck amount: ");
    String pulledAmtStr = promptString(("Enter pulled amount this period"));
        int paycheck = Integer.parseInt(paycheckStr);
        int pulledAmount = Integer.parseInt(pulledAmtStr);
        return (paycheck * .75) + pulledAmount;
    }

    public static String promptString(String message) {
        Scanner console = new Scanner(System.in);
        print(message);
        return console.nextLine();
    }

    public static void printd(double amt) {
        System.out.println(amt);
    }
    public static void print(String message) {
        System.out.println(message);

}
}

