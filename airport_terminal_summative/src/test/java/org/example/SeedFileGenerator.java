package org.example;

import org.example.model.CommercialAircraft;
import org.example.model.Flight;
import org.example.model.Passenger;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SeedFileGenerator {

    public static void main(String[] args) {
        String filePath = "src/test/resources/seed_reservations.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            Flight flight = new Flight(
                    "OF815",
                    LocalDate.of(2025, 7, 25),
                    new BigDecimal("399.99"),
                    new CommercialAircraft("Boeing 737", 150, 17.865, "Oceanic Airlines")
            );

            Passenger passenger = new Passenger("John Locke", "AB123456");

            writer.printf("%s,%s,%s,%s,%s,%s,%s\n",
                    flight.getFlightNumber(),
                    flight.getDepartureDate(),
                    flight.getTicketPrice(),
                    passenger.getName(),
                    passenger.getPassportNumber(),
                    flight.getAircraft().getModel(),
                    flight.getAircraft().getType()
            );

            System.out.println("Seed file created at: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to create seed file: " + e.getMessage());
        }
    }
}
