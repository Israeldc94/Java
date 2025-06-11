public class Locker {

    private String lockerNumber;
    private String contents;
    private boolean isOccupied;


    //sets up our public locker object for use in other files
    public Locker(String lockerNumber, String contents, boolean isOccupied){
        this.contents = "";
        this.lockerNumber = lockerNumber;
        this.isOccupied = false;

    }

    //allows for us to quickly get the locker number
    public String getLockerNumber(){

        return this.lockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    //allows for us to check if a locker object is rented
    public boolean isOccupied(){

        return isOccupied;
    }

    //the act of renting the locker. sets is rented to true and assigns contents
    public void store(String contents){
        this.contents = contents;
        this.isOccupied = true;
    }

    //act of releasing the locker. Sets contents to null and isRented to false
    public void release() {
        this.contents = null;
        this.isOccupied = false;
    }

    //allows for quick retrieval of a locker objects contents.
    public String getContents(){

        return this.contents;
    }


}



