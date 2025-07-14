package com.example.inventory_capstone.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InventoryFileSeeder {
    public static void seedInventoryFile(String sourcePath, String seedPath) {
        File sourceFile = new File(sourcePath);
        File seedFile = new File(seedPath);

        if (!sourceFile.exists()) {
            System.out.println("Source file not found: " + sourcePath);
            return;
        }

        try (
                FileInputStream inStream = new FileInputStream(sourceFile);
                FileOutputStream outStream = new FileOutputStream(seedFile)
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead =  inStream.read(buffer)) > 0) {
                outStream.write(buffer,0, bytesRead);
            }

            System.out.println("Seed file successfully created at: " + seedPath);

        } catch (IOException e) {
            System.out.println("Error creating seedFile: " +e.getMessage());
        }
    }
}
