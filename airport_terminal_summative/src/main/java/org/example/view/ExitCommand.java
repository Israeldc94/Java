package org.example.view;

import org.example.controller.ReservationService;
import org.example.model.Passenger;
import org.example.repository.FileRepository;
import org.example.repository.FlightRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class ExitCommand implements Command {

    private final ReservationService service;
    private final FileRepository fileRepo;
    private final FlightRepository flightRepo;
    private final TextIO io;

    public ExitCommand(ReservationService service,
                       FileRepository fileRepo,
                       FlightRepository flightRepo,
                       TextIO io) {

        this.service = service;
        this.fileRepo = fileRepo;
        this.flightRepo = flightRepo;
        this.io = io;
    }

    @Override
    public void execute() {

        io.print("Saving reservations....");
        fileRepo.saveReservations(service, flightRepo);
        io.print("Exiting system. Safe travels.");

    }
}
