import java.util.Random;

public class LockerService {
    Random random = new Random();
    IO io = new IO();

    int randomNumber = random.nextInt(90) + 10;

    private Locker[] lockers;

    public LockerService(int capacity) {
        lockers = new Locker[capacity];

        for (int i = 0; i < lockers.length; i++) {
            lockers[i] = new Locker(i + 1, null, false);
        }
    }

    public Locker[] getLockerList() {
        return lockers;
    }

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

    public Result accessLocker() {
        int lockerNumber = io.getInt("Enter your locker number: ", 1, 10);
        Locker locker = lockers[lockerNumber - 1];
        if (locker.isRented()) {
            String pin = io.getValidPin("Enter your pin number: ");
            if (locker.getPinNumber().equals(pin)) {
                return new Result(true, lockerNumber + " is open!");
            } else {
                return new Result(false, "This pin number is invalid");
            }

        } else {
            return new Result(false, "This locker is not rented");
        }
    }

    public Result releaseLocker() {
        int lockerNumber = io.getInt("Enter your locker number: ", 1, 10);
        Locker locker = lockers[lockerNumber - 1];
        if (locker.isRented()) {
            String pin = io.getValidPin("Enter your pin number: ");
            if (locker.getPinNumber().equals(pin)) {
                String choice = io.getNonEmptyString("Are you sure? (y/n)");
                if (choice.equals("y")) {
                    locker.release();
                    return new Result(true, lockerNumber + " is released! We hope you enjoyed the show.");
                } else if (choice.equals("n")) {
                    return new Result(false, lockerNumber + " is still being held. Enjoy the show.");

                } else {
                    return new Result(false, "Invalid input please try again");
                }
            } else {
                return new Result(false, "This pin number is invalid");
            }

        } else {
            return new Result(false, "This locker is not rented");
        }
    }

    public String setPinNumber() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(90) + 10;
        String pin = "00" + randomNumber;
        return pin;
    }

    public boolean lockersOccupied() {
        for (int i = 0; i < lockers.length; i++) {
            if (!lockers[i].isRented()) {
                return false;
            }
        }
        return true;


    }
}



