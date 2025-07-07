package org.example.view;

import org.example.controller.ReservationService;
import org.example.model.Passenger;
import org.example.repository.FileRepository;
import org.example.repository.FlightRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DeletePassengerCommand implements Command {

    private final ReservationService service;
    private final FileRepository fileRepo;
    private final FlightRepository flights;
    private final TextIO io;

    public DeletePassengerCommand(ReservationService service,
                                  FileRepository fileRepo,
                                  FlightRepository flights,
                                  TextIO io) {
        this.service = service;
        this.fileRepo = fileRepo;
        this.flights = flights;
        this.io = io;

    }
    @Override
    public void execute() {
        String flightNumber = io.getNonEmptyString("Enter flight number: ");

        if (!service.hasPassengers(flightNumber)) {
            io.print("No passengers found for that flight.");
            return;
        }

        ArrayList<Passenger> passengers = service.getPassengers(flightNumber);
        if (passengers.isEmpty()) {
            io.print("This flight has no passengers to delete.");
            return;
        }

        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            io.print((i+1) +". " + p.getName() + " | Passport: " + p.getPassportNumber());
        }
        int index = io.getInt("Enter passenger number to delete: ", 1, passengers.size());
        Passenger removed = passengers.remove(index - 1);
        io.print("Removed: " + removed.getName() +" from flight: " + flightNumber);

        fileRepo.saveReservations(service, flights);
    }
}
