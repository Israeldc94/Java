package org.example.controller;

import org.example.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationService {
    private HashMap<String , ArrayList<Passenger>> reservations = new HashMap<>();


    public ReservationService(HashMap<String, ArrayList<Passenger>> reservations) {

        this.reservations = reservations;
    }

    public HashMap<String, ArrayList<Passenger>> getReservations() {

        return reservations;
    }

    public ArrayList<Passenger> getPassengers(String flightNumber) {
        if(reservations.containsKey(flightNumber)) {
            return reservations.get(flightNumber);
        } else {
            return new ArrayList<>();
        }
    }

    public void addPassenger(String flightNumber, Passenger passenger) {
        if (!reservations.containsKey(flightNumber)) {
            reservations.put(flightNumber, new ArrayList<>());
        }
        reservations.get(flightNumber).add(passenger);
    }

    public boolean hasFlight(String flightNumber) {
        return reservations.containsKey(flightNumber);
    }

    public boolean hasPassengers(String flightNumber) {
        return hasFlight(flightNumber) && !reservations.get(flightNumber).isEmpty();
    }


}
