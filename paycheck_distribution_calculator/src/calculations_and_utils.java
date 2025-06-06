import java.util.Scanner;

public class calculations_and_utils {


    public DepositInfo getDepositInfo(){
    String paycheckStr = promptString("Enter paycheck amount: ");
    String pulledAmtStr = promptString(("Enter pulled amount this period"));
        double paycheck = Double.parseDouble(paycheckStr);
        int pulledAmount = Integer.parseInt(pulledAmtStr);
        return new DepositInfo(paycheck, pulledAmount);

    }

    public static String promptString(String message) {
        Scanner console = new Scanner(System.in);
        print(message);
        return console.nextLine();
    }

    public void printD(double amt) {
        System.out.printf("%.2f", amt);

    }
    public static void print(String message) {
        System.out.println(message);

}

}


