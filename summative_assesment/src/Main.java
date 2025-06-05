
public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        LockerService service = new LockerService(10);
        int choice;
        boolean keepRunning = true;

        //application loop
        while (keepRunning) {
            if (!service.lockersOccupied()) {
                choice = io.getInt(io.mainMenu(), 1, 4);
            } else {
                choice = io.getInt(io.mainMenufull(), 1, 4);
            }
            //menu decision tree
            switch (choice) {
                case 1:
                    Result result = service.rentLocker();
                    io.print(result.getMessage());
                    break;
                case 2:
                    Result result1 = service.accessLocker();
                    io.print(result1.getMessage());
                    break;
                case 3:
                    Result result2 = service.releaseLocker();
                    io.print(result2.getMessage());
                    break;
                case 4:
                    keepRunning = false;
                    break;





            }

        }
    }
}