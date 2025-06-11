
public class Main {
    private static final int RENT_LOCKER = 1;
    private static final int ACCESS_LOCKER = 2;
    private static final int RELEASE_LOCKER = 3;
    private static final int EXIT = 4;

    public static void main(String[] args) {

        //Calls the necessary classes and initializes crucial variables
        IO io = new IO();
        int capacity = 10;
        LockerService service = new LockerService(capacity);
        int choice;
        boolean keepRunning = true;
        int lockerNumber;
        String pin;

        //application loop if any of the lockers aren't rented the main menu displays. If they are all rented the mainMenuFull displays warning the user that all lockers are rented
        while (keepRunning) {
            if (!service.lockersOccupied()) {
                choice = io.getInt(io.mainMenu(), 1, 4);
            } else {
                choice = io.getInt(io.mainMenuFull(), 1, 4);
            }
            //menu decision tree
            switch (choice) {

                //rents a locker for the user and returns the locker number and pin if successful.
                case RENT_LOCKER:
                    Result result1 = service.rentLocker();
                    io.print(result1.getMessage());
                    break;

                //grants user access to the locker if the proper number and pin combination are provided
                case ACCESS_LOCKER:
                    lockerNumber = service.getUserLockerNumber();
                    if (lockerNumber <= capacity && lockerNumber >= 1) {
                        pin = service.getUserPin();
                        if (!(pin.equals("0"))) {
                            Result result2 = service.lockerValidation(lockerNumber, pin);
                            io.print(result2.getMessage());
                        }
                    }
                    break;

                    //releases a locker for the user if the proper locker number and pin combination are provided
                case RELEASE_LOCKER:
                    lockerNumber = service.getUserLockerNumber();
                    if (lockerNumber <= capacity && lockerNumber >= 1) {
                        pin = service.getUserPin();
                        if (!(pin.equals("0"))) {
                            Result result3 = service.lockerValidation(lockerNumber, pin);

                            if (result3.isSuccess()) {
                                Result resultRelease = service.releaseLocker(lockerNumber, pin);
                                io.print(resultRelease.getMessage());

                            } else {
                                io.print(result3.getMessage());
                            }
                        }
                    }
                    break;

                //quit option for the user. Closes the application
                case EXIT:
                    keepRunning = false;
                    break;
            }
        }
    }
}