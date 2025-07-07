package org.example.repository;

import org.example.controller.ReservationService;
import org.example.model.Passenger;
import java.util.ArrayList;
import java.util.HashMap;

public interface FileRepository {

    public void saveReservations(ReservationService service, FlightRepository flightRepo);

    public HashMap<String, ArrayList<Passenger>> getReservationsFromFile();

    public void setFilePath(String path);

    public String getFilePath();

    public void appendReservations(ReservationService service, FlightRepository flights);

}
