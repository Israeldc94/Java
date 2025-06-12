import java.util.Scanner;

public class IO {
    private final Scanner console;

    //allows the use of IO in other files and is the one place where Scanner(System.in) is called. We initialized it to console.
    public IO() {
        this.console = new Scanner(System.in);
    }

    //prompts the user for an integer within a range and validates the input
    public int getInt(String prompt, int min, int max) {
        int result = 0;
        print(prompt);
        String resultStr = console.nextLine();
        try {
            result = Integer.parseInt(resultStr);
            if (result < min || result > max) {
                print("\nThat option does not exist");
            } else {
                return result;
            }
        } catch (NumberFormatException ex) {
            print("\nInvalid input. Please enter a valid number.");
        } catch (NullPointerException ex){
            print("\nInvalid input");
        }
        return result;
    }
    //prompts the user for input and checks to see if its empty
    public String getNonEmptyString(String prompt) {
        String input;
        print(prompt);
        input = console.nextLine();
        if (input.equals("")) {
            print("\nInvalid input");
            return "0";
        } else {
            return input;
        }
    }



    //prints our messages, the one use of system.out.println
    public void print(String message) {
        System.out.println(message);
    }

    //establishes our main menu
    public String mainMenu(){
        return "\nWelcome to the Storage Locker Manager! \n\t\t 1. Add Locker \n\t\t 2. Remove Locker \n\t\t 3. Store Item \n\t\t 4. Retrieve Item \n\t\t 5. Display All lockers \n\t\t6. Exit ";
    }

}


