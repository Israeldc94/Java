import java.util.Scanner;

public class IO {
    private final Scanner console;

    public IO() {
        this.console = new Scanner(System.in);
    }

    public int getInt(String prompt, int min, int max) {
        int result = 0;
        print(prompt);
        String resultStr = console.nextLine();
        try {
            result = Integer.parseInt(resultStr);
            if (result < min || result > max) {
                print("That option does not exist");
            } else {
                return result;
            }
        } catch (NumberFormatException ex) {
            print("Invalid input. Please enter a valid number.");
        }
        return result;
    }

    public String getNonEmptyString(String prompt) {
        String input;
        print(prompt);
        input = console.nextLine();
        if (input.equals("")) {
            print("Invalid input");
            return input;
        } else {
            return input;
        }
    }

    public String getValidPin(String prompt) {
        print(prompt);
        String input = console.nextLine();
        if (input.length() != 4) {
            print("Pin must be 4 digits");
            return null;
        } else {
            return input;
        }
    }
    public void print(String message) {
        System.out.println(message);
    }
    public String mainMenu(){
        return "What would you like to do next? \n\t\t 1. Rent a locker \n\t\t 2.Access a locker \n\t\t 3.Release a locker";
    }
    public String mainMenufull(){
        return "What would you like to do next? \n\t\t 1. (All lockers are occupied.) \n\t\t 2. Access a locker \n\t\t 3. Release a locker";
    }
}
