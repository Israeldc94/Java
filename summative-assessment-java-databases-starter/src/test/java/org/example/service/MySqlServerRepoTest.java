package org.example.service;

import org.example.data.ServerRepo;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.Server.MySqlServerRepo;
import org.example.model.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlServerRepoTest {

    private ServerRepo repo;

    @BeforeEach
    void setup() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/SimpleBistroTest");
        ds.setUsername("root");
        ds.setPassword("Cerseiwatch304!");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("CALL set_known_good_state();");

        repo = new MySqlServerRepo(jdbcTemplate);
    }

    @Test
    void shouldReturnServerByID() throws Exception {
        Server server = repo.getServerById(1);
        assertNotNull(server);
        assertEquals(1, server.getServerID());
        assertEquals("Mersey", server.getFirstName());
    }

    @Test
    void shouldThrowNotFoundForMissingId() {
        assertThrows(RecordNotFoundException.class, () -> repo.getServerById(9999));
    }

    @Test
    void shouldReturnAvailableServers() throws Exception {
        List<Server> servers = repo.getAllAvailableServers(LocalDate.of(2023, 5, 1));
        assertNotNull(servers);
        assertFalse(servers.isEmpty());
        assertTrue(servers.stream().allMatch(s -> s.getHireDate().isBefore(LocalDate.now())));
    }

    @Test
    void shouldReturnEmptyListForUnavailableDate() throws Exception {
        List<Server> servers = repo.getAllAvailableServers(LocalDate.of(1800, 1, 1));
        assertNotNull(servers);
        assertTrue(servers.isEmpty());
    }
}
