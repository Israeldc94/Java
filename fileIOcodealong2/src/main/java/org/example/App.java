package org.example;

//Required imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("hydrogen");
        lines.add("helium");
        lines.add("lithium");


        // Create a file and write to it.

        Path path = Paths.get("elements.txt");
        try {
            Files.write(path, lines, StandardOpenOption.CREATE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        lines.clear();
        lines.add("beryllium");
        lines.add("boron");
        lines.add("carbon");

        // Append to a file

        try {
            Files.write(path, lines, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //Read from the file.

        try {
            for (String line : Files.readAllLines(path)) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Delete the file.

        try {
            boolean success = Files.deleteIfExists(path);
            if (success) {
                System.out.println("\nelements.txt was deleted");
            } else {
                System.out.println("elements.txt was NOT deleted");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
