public class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance){
        this.owner = owner;
        this.balance = balance;
    }

    public void display_Account(){
        System.out.printf("%n Initial balance (%s): %.2f %n", this.owner, this.balance);
    }
    public double deposit(double amount){
        this.balance += amount;
        return amount;
    }
}
