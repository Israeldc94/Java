package org.example.view;

import org.example.controller.ReservationService;
import org.example.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewPassengersCommand implements Command {

    private final ReservationService service;
    private final TextIO io;

    public ViewPassengersCommand(ReservationService service, TextIO io) {
        this.service = service;
        this.io = io;
    }


    @Override
    public void execute() {
        String flightNumber = io.getNonEmptyString("Enter flight number: ");

        if(!service.hasPassengers(flightNumber)) {
            io.print("No reservations found for flight: " + flightNumber);
            return;
        }

        ArrayList<Passenger> passengers = service.getPassengers(flightNumber);
        if (passengers.isEmpty()) {
            io.print("There are currently no passengers booked for this flight.");
            return;
        }

        io.print("\nPassengers for flight " + flightNumber + ":");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            io.print((i + 1) + "." + p.getName() + " | Passport: " +p.getPassportNumber());
        }
    }
}
