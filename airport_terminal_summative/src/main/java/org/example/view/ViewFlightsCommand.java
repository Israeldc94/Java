package org.example.view;

import org.example.model.Flight;
import org.example.repository.FlightRepository;

public class ViewFlightsCommand implements Command {

    private final FlightRepository flightRepo;
    private final TextIO io;

    public ViewFlightsCommand(FlightRepository flightRepo, TextIO io) {
        this.flightRepo = flightRepo;
        this.io = io;
    }


    @Override
    public void execute() {
        io.print("Available flights");
        for (Flight flight : flightRepo.getFlights()) {
            io.print(String.format("Flight %s | Date: %s | Price $%s | Aircraft: %s (%s)",
                    flight.getFlightNumber(),
                    flight.getDepartureDate(),
                    flight.getTicketPrice(),
                    flight.getAircraft().getModel(),
                    flight.getAircraft().getType()
            ));
        }

    }
}
