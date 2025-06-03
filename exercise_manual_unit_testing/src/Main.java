import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String choice = " ";
        selectDrink("WATER");

    }

    public static String selectDrink(String choice) {
        System.out.println("Received input: " + choice);
        choice = choice.toLowerCase();
        System.out.println("Lowercased input: " + choice);
        if (choice.equals("water")){
            System.out.println("Selected: You selected Water");
            return ("You selected Water");
        } else if (choice.equals("soda")) {
            System.out.println("Selected: You selected Soda");
            return ("You selected Soda");
        } else if (choice.equals("juice")) {
            System.out.println("Selected: You selected Juice");
            return ("You selected Juice");
        }else{
            return ("Invalid selection");
        }
    }
    public static void runTests(){
        String [] inputs = {"water", "soda","juice", "WATER", "Tea", " "};
        String [] outputs = {"You selected Water", "You selected Soda", "You selected Juice", "You selected Water",
                "Invalid selection", "Invalid selection"};
        for ( int i = 0; i < inputs.length; i++){
            String input = inputs[i];
            String output = outputs [i];
            System.out.println("Testing with " + input + "...");
            String actual = selectDrink(input);
            if (output.equals(actual)) {
                System.out.println("Passed!");
            }else{
                System.out.println("FAIL: Expected '" + output + "', got '" +actual + "'");
            }
        }

    }
}