
public class Locker {

    private String lockerNumber;
    private String contents;
    private boolean isOccupied;


    //sets up our public locker object for use in other files
    public Locker(String lockerNumber, String contents, boolean isOccupied) {
        this.contents = "";
        this.lockerNumber = lockerNumber;
        this.isOccupied = false;

    }

    //allows for us to quickly get the locker number
    public String getLockerNumber() {

        return this.lockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    //allows for us to check if a locker object is rented
    public boolean isOccupied() {

        return isOccupied;
    }



    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }



    //allows for quick retrieval of a locker objects contents.
    public String getContents() {
        return this.contents;
    }

    public String toString(Locker locker) {
        return lockerNumber + " contents: " + contents + " occupied?: " + isOccupied;
    }

}



