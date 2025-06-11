
public class Main {
    public static void main(String[] args) {

    //Calls the necessary classes and initializes crucial variables
        IO io = new IO();
        LockerService service = new LockerService(10);
        int choice;
        boolean keepRunning = true;

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
                case 1:
                    Result result1 = service.rentLocker();
                    io.print(result1.getMessage());
                    break;

                //grants user access to the locker if the proper number and pin combination are provided
                case 2:
                    Result result2 = service.accessLocker();
                    io.print(result2.getMessage());
                    break;

                //releases a locker for the user if the proper locker number and pin combination are provided
                case 3:
                    Result result3 = service.releaseLocker();
                    io.print(result3.getMessage());
                    break;

                //quit option for the user. Closes the application
                case 4:
                    keepRunning = false;
                    break;

            }
        }
    }
}