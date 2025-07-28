package org.example.data.impl.Server;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MySqlServerRepo implements ServerRepo {

    private final JdbcTemplate jdbc;

    @Autowired
    public MySqlServerRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;

    }

    @Override
    public Server getServerById(int id) throws InternalErrorException, RecordNotFoundException {
        String sql = "SELECT * FROM Server WHERE ServerID = ?";

        try {
            return jdbc.queryForObject(sql, new ServerMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException();
        } catch (Exception ex) {
            throw new InternalErrorException();
        }
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        String sql = """
                SELECT * FROM Server
                Where ? BETWEEN HireDate AND IFNULL (TermDate, CURRENT_DATE)
                """;

        try {
            return jdbc.query(sql, new ServerMapper(), date);
        } catch (Exception ex) {
            throw new InternalErrorException();
        }
    }
}
