import java.util.Random;

public class LockerService {
    //assigns io to call our input/output class easily
    IO io = new IO();

    //sets up our list of lockers and the capacity
    private final Locker[] lockers;
    private final int capacity;

    //sets the capacity for our locker list.
    public LockerService(int capacity) {
        lockers = new Locker[capacity];
        this.capacity = capacity;

        // creates our list of locker objects.
        for (int i = 0; i < lockers.length; i++) {
            lockers[i] = new Locker(i + 1, null, false);
        }
    }

    //This method finds the first unrented locker in our list, assigns a pin number to it and presents the data to the user.
    public Result rentLocker() {
        for (int i = 0; i < lockers.length; i++) {
            if (!lockers[i].isRented()) {
                String pin = setPinNumber();
                lockers[i].rent(pin);
                return new Result(true, String.format("You rented locker number %d with pin %s", i + 1, pin));
            }

        }
        return new Result(false, "No lockers are available.");
    }

    /*gets a locker number from the user and checks the list for a matching number the function then requests a pin if they find a match for that locker.
    After confirming with the user the locker is then released.
     */
    public Result releaseLocker(int lockerNumber, String pin) {
        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];
            if (locker.getLockerNumber() == lockerNumber)
                if (locker.isRented()) {
                    if (locker.getPinNumber().equals(pin)) {
                        String choice = io.getNonEmptyString("\nAre you sure? (y/n)");
                        if (choice.equals("y")) {
                            locker.release();
                            return new Result(true, "Locker: " + lockerNumber + " is released! We hope you enjoyed the show.");
                        } else if (choice.equals("n")) {
                            return new Result(false, lockerNumber + " is still being held. Enjoy the show.");
                        }
                    }
                }
        }
        return new Result(false, "Something went wrong. Unable to find locker");
    }

    //sets up a random 2-digit number and concatenates it with "00" to create a pin
    public String setPinNumber() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(90) + 10;
        String pin = "00" + randomNumber;
        return pin;
    }

    //checks if all lockers are rented we will use this to print our alternative menu option
    public boolean lockersOccupied() {
        for (int i = 0; i < lockers.length; i++) {
            if (!lockers[i].isRented()) {
                return false;
            }
        }
        return true;


    }

    //This method allows user to access their locker by prompting for the locker number and pin. If there is an object in our list that matches the data entered the locker is opened.
    public Result lockerValidation(int lockerNumber, String pin) {
        for (int i = 0; i < lockers.length; i++) {
            Locker locker = lockers[i];
            if (locker.getLockerNumber() == lockerNumber) {
                if (locker.isRented()) {
                    if (locker.getPinNumber().equals(pin)) {
                        return new Result(true, "locker " + locker.getLockerNumber() + " has been accessed");
                    } else {
                        return new Result(false, "This pin number is incorrect");
                    }
                }else{
                    return new Result(false, "Locker not rented");
                }
            }
        }
        return new Result(false, "Something went wrong. Locker not found");
    }

    //prompts the user for a locker number and validates the input
    public int getUserLockerNumber() {
        int lockerNumber = io.getInt("Enter your locker number: ", 1, capacity);
        if (!(lockerNumber == 0)) {
            return lockerNumber;
        }
        return 0;
    }

    //prompts the user for a pin and checks to make sure it is 4 characters
    public String getUserPin(){
        String pin =  io.getNonEmptyString("Enter your locker pin: ");
        if (io.getValidPin(pin) == "0"){
            return "0";
        } else {
            return pin;
        }
    }

}




