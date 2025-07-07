package org.example;
import org.example.controller.ReservationService;
import org.example.model.Passenger;
import org.example.repository.FileRepositoryCSV;
import org.example.repository.SampleFlightRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class FileRepositoryTest {

    static final String SEED_FILE_PATH = "src/test/resources/seed_reservations.csv";
    static final String TEST_FILE_PATH = "src/test/resources/test_reservations.csv";

    FileRepositoryCSV repo = new FileRepositoryCSV(TEST_FILE_PATH);
    File testFile;


    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
        testFile = testPath.toFile();
    }

    @Test
    public void testFileExists() {
        assertTrue(testFile.exists());
    }

    @Test
    public void testSaveReservations_writesExpectedCSV() throws IOException {
        SampleFlightRepository sampleRepo = new SampleFlightRepository();
        ArrayList<Passenger> OF316 = new ArrayList<>();
        ArrayList<Passenger> OF815 = new ArrayList<>();
        Passenger Locke = new Passenger("John Locke", "LS85467");
        Passenger Snake = new Passenger("Solid Snake", "AC87345");
        OF815.add(Locke);
        OF316.add(Snake);

        HashMap<String, ArrayList<Passenger>> reservations = new HashMap<>();
        reservations.put("OF316", OF316);
        reservations.put("OF815", OF815);

        ReservationService service = new ReservationService(reservations);
        repo.saveReservations(service, sampleRepo);

        ArrayList<String> lines = new ArrayList<>(Files.readAllLines(testFile.toPath()));

        assertEquals(2, lines.size());

        boolean foundLocke = false;
        boolean foundSnake = false;

        for (String line : lines) {
            if (line.contains("John Locke")) foundLocke = true;
            if (line.contains("Solid Snake")) foundSnake = true;
        }

        assertTrue(foundLocke);
        assertTrue(foundSnake);
    }


    @Test
    public void testGetReservations_rebuildsPassengerHashMapFromCSV() throws IOException {
        SampleFlightRepository sampleRepo = new SampleFlightRepository();

        HashMap<String, ArrayList<Passenger>> appendingMap = new HashMap<>();
        ArrayList<Passenger> OF316 = new ArrayList<>();
        OF316.add(new Passenger("Solid Snake", "FOX007"));
        appendingMap.put("OF316", OF316);

        ReservationService service = new ReservationService(appendingMap);

        repo.appendReservations(service, sampleRepo);


        HashMap<String, ArrayList<Passenger>> loaded = repo.getReservationsFromFile();

        assertNotNull(loaded);
        assertTrue(loaded.containsKey("OF815"));
        assertTrue(loaded.containsKey("OF316"));

        ArrayList<Passenger> OF815 = loaded.get("OF815");
        OF316 = loaded.get("OF316");

        assertEquals(1, OF815.size());
        assertEquals(1, OF316.size());
    }

}




