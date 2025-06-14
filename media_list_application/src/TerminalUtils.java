import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class TerminalUtils {
    Scanner io = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    }

    public String getNonEmptyString(String prompt) {
        String input;
        print(prompt);
        input = io.nextLine();
        if (input.equals("")) {
            print("\nInvalid input");
            return input;
        } else {
            return input;
        }
    }

    public int getInt(String prompt, int min, int max) {
        int num = 0;
        print(prompt);
        String resultStr = io.nextLine();
        try {
            num = Integer.parseInt(resultStr);
            if (num < min || num > max) {
                print("\nThat option does not exist");
                return num;
            } else {
                return num;
            }
        } catch (NumberFormatException ex) {
            print("\nInvalid input. Please enter a valid number.");
        } catch (NullPointerException ex) {
            print("\nInvalid input");
        }
        return num;
    }

    public void displayMenu() {
        print("\n=== Media List Application ===\n1. Add Media \n2. Remove Media \n3. Play Media \n4. List All Media \n5. Quit");
    }

    public void displayMenu2() {
        print("Select media type: \n1. Video \n2. Audio \n3. Image \n4. Book");
    }


    public int getMenuChoice() {
        return getInt("Choose an option: ", 1, 5);
    }

    public int getMenuChoice2() {
        return getInt("Choose type: ", 1, 4);
    }


    public void displayMediaList(List<Media> mediaList) {
        for (int i = 0; i < mediaList.size(); i++) {
            print(mediaList.get(i).toString());
        }
    }
}

