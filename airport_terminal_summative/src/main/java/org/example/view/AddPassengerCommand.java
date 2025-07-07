package org.example.view;

import org.example.controller.ReservationService;
import org.example.model.Passenger;
import org.example.repository.FileRepository;
import org.example.repository.FlightRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class AddPassengerCommand implements Command {
    private final ReservationService service;
    private final FlightRepository flightRepo;
    private final FileRepository fileRepo;
    private final TextIO io;

    public AddPassengerCommand(ReservationService service,
                               FlightRepository flightRepo,
                               FileRepository fileRepo,
                               TextIO io) {
        this.service = service;
        this.flightRepo = flightRepo;
        this.fileRepo = fileRepo;
        this.io = io;
    }

    @Override
    public void execute() {
        String flightNumber  = io.getNonEmptyString("Enter flight number: ");

        if (flightRepo.findFlightByNumber(flightNumber) == null) {
            io.print("That flight does not exist. Please check the flight number and try again.");
            return;
        }

        String name = io.getNonEmptyString("Enter passenger name: ");
        String passport = io.getNonEmptyString("Enter passport number: ");

        Passenger newPassenger = new Passenger(name, passport);
        service.addPassenger(flightNumber, newPassenger);


        fileRepo.saveReservations(service, flightRepo);
        io.print("Passenger added to flight: " + flightNumber + ".");
    }
}
