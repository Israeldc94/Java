package org.example.repository;

import org.example.model.Aircraft;
import org.example.model.CommercialAircraft;
import org.example.model.Flight;
import org.example.model.PrivateJet;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class TrueFlightRepository implements FlightRepository {
    private ArrayList<Flight> flights;


    public TrueFlightRepository() {
        flights = new ArrayList<>();
        Aircraft Boeing = new CommercialAircraft("Boeing 737", 150, 17.865, "Oceanic Airlines");
        Aircraft G650 = new PrivateJet("G650", 25, 6550, true, 704);
        Flight OF316 = new Flight("OF316", LocalDate.of(2025, 7, 25), new BigDecimal("399.99"), Boeing);
        Flight OF815 = new Flight("OF815", LocalDate.of(2025, 7, 25), new BigDecimal("399.99"), Boeing);
        Flight X007 = new Flight("X007", LocalDate.of(2025, 7, 25), new BigDecimal("399.99"), G650);
        flights.add(OF316);
        flights.add(OF815);
        flights.add(X007);


    }

    @Override
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    @Override
    public Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}

