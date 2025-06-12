
public class Main {
    private static final int ADD_LOCKER = 1;
    private static final int REMOVE_LOCKER = 2;
    private static final int STORE_ITEM = 3;
    private static final int RETRIEVE_ITEM = 4;
    private static final int DISPLAY_ALL_LOCKERS = 5;
    private static final int EXIT = 6;

    public static void main(String[] args) {
        IO io = new IO();
        LockerManager service = new LockerManager();
        int choice;
        String lockerNumber;

        boolean keepRunning = true;

        while (keepRunning) {
            choice = io.getInt(io.mainMenu(), 1, 6);

            switch (choice) {

                case ADD_LOCKER:
                    lockerNumber = io.getNonEmptyString("Enter a locker number: ");
                    if (!(lockerNumber.equals("0"))) {
                        io.print(service.addLocker(lockerNumber));
                    } else {
                        io.print(lockerNumber + " could not be added");
                    }
                    break;

                case REMOVE_LOCKER:
                    lockerNumber = io.getNonEmptyString("Enter a locker number: ");
                    if (!(lockerNumber.equals("0"))) {
                        io.print(service.releaseLocker(lockerNumber));
                    } else {
                        io.print(lockerNumber + " could not be found");
                    }
                    break;
                case STORE_ITEM:
                    lockerNumber = io.getNonEmptyString("Enter a locker number: ");
                    if (!(lockerNumber.equals("0"))) {
                        String contents = io.getNonEmptyString("What would you like to store?");
                        io.print(service.store(lockerNumber, contents));
                    }else {
                        io.print("Locker not found");
                    }
                    break;

                case RETRIEVE_ITEM:
                    lockerNumber = io.getNonEmptyString("Enter a locker number: ");
                    if (!(lockerNumber.equals("0"))) {
                        io.print(service.removeContents(lockerNumber));
                    }
                    break;
                case DISPLAY_ALL_LOCKERS:
                    try {
                        io.print(service.displayAllLockers());
                    } catch (IndexOutOfBoundsException e) {
                        io.print("No lockers set up");
                    }
                    break;

                case EXIT:
                    keepRunning = false;
            }
        }
    }
}