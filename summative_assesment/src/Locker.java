


public class Locker {
    private final int lockerNumber;
    private String pinNumber;
    private boolean isRented;

    //sets up our public locker object for use in other files
    public Locker(int lockerNumber, String pinNumber, boolean isRented){
        this.pinNumber = pinNumber;
        this.lockerNumber = lockerNumber;
        this.isRented = isRented;
    }

    //allows for us to quickly get the locker number
    public int getLockerNumber(){
        return this.lockerNumber;
    }
    //allows for us to check if a locker object is rented
    public boolean isRented(){
        return isRented;
    }

    //the act of renting the locker. sets is rented to true and assigns pin number
    public void rent(String pin){
        this.pinNumber = pin;
        this.isRented = true;
    }

    //act of releasing the locker. Sets pin number to null and isRented to false
    public void release() {
        this.pinNumber = null;
        this.isRented = false;
    }

    //allows for quick retrieval of a locker objects pin.
    public String getPinNumber(){

        return this.pinNumber;
    }


}

