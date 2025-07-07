package org.example.repository;

import java.io.*;

import org.example.controller.ReservationService;
import org.example.model.Flight;
import org.example.model.Passenger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileRepositoryCSV implements FileRepository {
    private String filePath;

    public FileRepositoryCSV(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void saveReservations(ReservationService service, FlightRepository flights) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            HashMap<String, ArrayList<Passenger>> reservations = service.getReservations();

            for (Map.Entry<String, ArrayList<Passenger>> entry : reservations.entrySet()) {
                String flightNumber = entry.getKey();
                Flight flight = flights.findFlightByNumber(flightNumber);


                for (Passenger p : entry.getValue()) {
                    writer.printf("%s,%s,%s,%s,%s,%s,%s\n",
                            flight.getFlightNumber(),
                            flight.getDepartureDate(),
                            flight.getTicketPrice(),
                            p.getName(),
                            p.getPassportNumber(),
                            flight.getAircraft().getModel(),
                            flight.getAircraft().getType());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, ArrayList<Passenger>> getReservationsFromFile() {
        HashMap<String, ArrayList<Passenger>> reservations = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String flightNumber = parts[0];
                LocalDate departureDate = LocalDate.parse(parts[1]);
                BigDecimal ticketPrice = new BigDecimal(parts[2]);
                String passengerName = parts[3];
                String passportNumber = parts[4];
                String aircraftModel = parts[5];
                String aircraftType = parts[6];

                Passenger passenger = new Passenger(passengerName, passportNumber);

                if (!reservations.containsKey(flightNumber)) {
                    reservations.put(flightNumber, new ArrayList<>());
                }

                reservations.get(flightNumber).add(passenger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void setFilePath(String path) {

    }

    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public void appendReservations(ReservationService service, FlightRepository flights) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {

            HashMap<String, ArrayList<Passenger>> reservations = service.getReservations();

            for (Map.Entry<String, ArrayList<Passenger>> entry : reservations.entrySet()) {
                String flightNumber = entry.getKey();
                Flight flight = flights.findFlightByNumber(flightNumber);

                if (flight == null) {
                    throw new IllegalArgumentException("Flight number not found: " + flightNumber);
                }

                for (Passenger p : entry.getValue()) {
                    writer.printf("%s,%s,%s,%s,%s,%s,%s\n",
                            flight.getFlightNumber(),
                            flight.getDepartureDate(),
                            flight.getTicketPrice(),
                            p.getName(),
                            p.getPassportNumber(),
                            flight.getAircraft().getModel(),
                            flight.getAircraft().getType());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
