import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = 0;
        try {
            System.out.print("Enter your age: ");
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" this is not a valid age");
        }
        String email = null;
        try {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
        } catch (NullPointerException ex) {
            System.out.println("No input found");
        }
        int pin = 0;
        try {
            System.out.print("Enter your 4-digit PIN: ");
            String pinstr = scanner.nextLine();
            if (pinstr.length() != 4) {
                throw new IllegalArgumentException("Pin must be 4 digits");
            }
            pin = Integer.parseInt(pinstr);

        } catch (NullPointerException ex) {
            System.out.println("No input found");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input");
        } finally {
            System.out.println("Registration Attempt Complete!");
        }
        System.out.println("\nRegistration Successful!");
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("PIN: " + pin);
    }
}
