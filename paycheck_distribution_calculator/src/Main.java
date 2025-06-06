
public class Main {
    static calculations_and_utils calc = new calculations_and_utils();
    public static void main(String[] args) {

        DepositInfo info = calc.getDepositInfo();
        System.out.printf("Deposit amount: %.2f" ,info.depositAmount);
        System.out.printf("\nPersonal amount: %.2f" , info.personal());

    }

}