package org.example.repository;

import org.example.model.Aircraft;
import org.example.model.CommercialAircraft;
import org.example.model.Flight;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.ArrayList;

public class SampleFlightRepository implements FlightRepository{
    private ArrayList<Flight> flights;



    public SampleFlightRepository() {
        flights = new ArrayList<>();
        Aircraft a1 = new CommercialAircraft("Boeing 737", 150, 17.865, "Oceanic Airlines");
        Flight flight = new Flight("OF316", LocalDate.of(2025, 7, 25), new BigDecimal("399.99"), a1);
        Flight flight2 = new Flight("OF815", LocalDate.of(2025,7,25), new BigDecimal("399.99"), a1);
        flights.add(flight);
        flights.add(flight2);
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
