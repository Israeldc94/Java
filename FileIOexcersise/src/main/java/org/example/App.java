package org.example;

import java.io.*;

public class App
{
    public static void main( String[] args )
    {
        File file = new File("students.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("students.txt created");
            } else {
                System.out.println("students.txt already exists");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter("students.txt")) {
            writer.println("Alice, A");
            writer.println("Bob, B");
            writer.println("Charlie, A+");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter("students.txt", true);
        PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println("David, B+");
            writer.println("Eva, A");

    } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (FileReader fileReader = new FileReader("students.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }
            } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean success = file.delete();
        if (success) {
            System.out.println("\nstudents.txt was deleted.");
        } else {
            System.out.println("\nstudents.txt was NOT deleted");
        }
    }
}
