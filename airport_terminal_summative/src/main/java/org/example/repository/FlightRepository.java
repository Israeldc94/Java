package org.example.repository;

import org.example.model.Flight;

import java.util.ArrayList;

public interface FlightRepository {
ArrayList<Flight> getFlights();

Flight findFlightByNumber(String flightNumber);
}
