public class DepositInfo {
    public double paycheck;
    public int pulledAmount;
    public double depositAmount;

    public DepositInfo(double paycheck, int pulledAmount) {
        this.paycheck = paycheck;
        this.pulledAmount = pulledAmount;
        this.depositAmount = (paycheck * .75) + pulledAmount;
    }

    public double personal(){
        return paycheck - depositAmount;
    }
}
