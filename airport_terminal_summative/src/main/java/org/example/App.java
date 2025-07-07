package org.example;

import org.example.controller.ReservationService;
import org.example.model.Flight;
import org.example.model.Passenger;
import org.example.repository.FileRepository;
import org.example.repository.FileRepositoryCSV;
import org.example.repository.FlightRepository;
import org.example.repository.TrueFlightRepository;
import org.example.view.*;

import java.util.ArrayList;
import java.util.HashMap;

public class App 
{
    public static void main( String[] args ) {
        TextIO io = new ConsoleIO();
        FileRepository file = new FileRepositoryCSV("src/test/resources/reservations.csv");
        HashMap<String, ArrayList<Passenger>> loaded = file.getReservationsFromFile();
        ReservationService service = new ReservationService(loaded);
        FlightRepository flights = new TrueFlightRepository();
        ViewFlightsCommand viewFlights = new ViewFlightsCommand(flights, io);
        ViewPassengersCommand viewPassengers = new ViewPassengersCommand(service, io);
        AddPassengerCommand addPassenger = new AddPassengerCommand(service, flights, file, io);
        DeletePassengerCommand deletePassenger = new DeletePassengerCommand(service, file, flights, io);
        ExitCommand exit = new ExitCommand(service, file, flights, io);



        boolean keepRunning = true;

        while (keepRunning) {
            io.printMenu();

            int choice = io.getInt("Select an option: ", 1, MenuOptions.values().length);
            MenuOptions selected = MenuOptions.values()[choice - 1];

            switch (selected) {
                case VIEW_ALL_FLIGHTS:
                    viewFlights.execute();
                    break;
                case VIEW_PASSENGERS_FOR_FLIGHT:
                    viewPassengers.execute();
                    break;
                case ADD_PASSENGER:
                    addPassenger.execute();
                    break;
                case REMOVE_PASSENGER:
                    deletePassenger.execute();
                    break;
                case EXIT:
                    exit.execute();
                    keepRunning = false;
                    break;
            }
        }



    }
}
