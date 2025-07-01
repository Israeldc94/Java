package org.example;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class App 
{
    public static void main( String[] args ) {
      File file = new File("colors.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("colors.txt created");
            } else {
                System.out.println("colors.txt already exists");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("File contents");




         try (PrintWriter writer = new PrintWriter("colors.txt")) {

            writer.println("red");
            writer.println("blue");
            writer.println("yellow");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        }

        try (FileWriter fileWriter = new FileWriter("colors.txt", true);
        PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println("green");
            writer.println("orange");
            writer.println("purple");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (FileReader fileReader = new FileReader("colors.txt");
             BufferedReader reader = new BufferedReader(fileReader)) {

            // When there are no more lines, readLine() returns null.
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        boolean success = file.delete();
        if (success) {
            System.out.println("\ncolors.txt was deleted.");
        } else {
            System.out.println("colors.txt was not deleted. ");
        }


    }

    }

