import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Locker {
    private int lockerNumber;
    private String pinNumber;
    private boolean isRented;

    public Locker(int lockerNumber, String pinNumber, boolean isRented){
        this.pinNumber = pinNumber;
        this.lockerNumber = lockerNumber;
        this.isRented = isRented;
    }

    public boolean isRented(){
        return isRented;
    }

    public void rent(String pin){
        this.pinNumber = pin;
        this.isRented = true;
    }

    public void release() {
        this.pinNumber = null;
        this.isRented = false;
    }
    public int getLockerNumber() {
        return lockerNumber;
    }
    public String getPinNumber(){
        return this.pinNumber;
    }


}

