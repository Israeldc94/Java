package org.example;
import java.util.Scanner;

public class IO {
    Scanner console = new Scanner(System.in);

    public String getNonEmptyString(String prompt) {
        String input;
        print(prompt);
        input = console.nextLine();
        if (input.isEmpty()) {
            print("\nInvalid input");
            return input;
        } else {
            return input;
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public int getInt(String prompt, int min, int max) {
        int num = 0;
        print(prompt);
        String resultStr = console.nextLine();
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

    public void report1(Artifact artifact) {
        print("Artifact: " + artifact.getName() + " (" + artifact.getYearOfDiscovery() + ")");
        print("Discoverer and curator: " + artifact.getDiscoverer().getFirstName() + artifact.getDiscoverer().getLastName() + ", " + artifact.getDiscoverer().getPrimarySpecialty());
    }

    public void report2(Artifact artifact) {
        print("Artifact: " + artifact.getName() + " (" + artifact.getYearOfDiscovery() + ")");
        print("Discoverer: " + artifact.getDiscoverer().getFirstName() + artifact.getDiscoverer().getLastName() + ", " + artifact.getDiscoverer().getPrimarySpecialty());
        print("Curator: " + artifact.getCurator().getFirstName() + artifact.getCurator().getLastName() + ", " + artifact.getCurator().getPrimarySpecialty());

    }
}

