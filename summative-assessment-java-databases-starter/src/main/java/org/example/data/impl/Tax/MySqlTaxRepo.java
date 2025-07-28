package org.example.data.impl.Tax;

import org.example.data.TaxRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Tax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class MySqlTaxRepo implements TaxRepo {
    JdbcTemplate jdbc;

    @Autowired
    public MySqlTaxRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }




    @Override
    public Tax getCurrentTax(LocalDate dateOf) throws InternalErrorException, RecordNotFoundException {
        String sql = "SELECT * FROM tax WHERE ? BETWEEN StartDate AND IFNULL(EndDate, CURRENT_DATE)";

        try {
            return jdbc.queryForObject(sql, new TaxMapper(), dateOf);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException();
        } catch (Exception ex) {
            throw new InternalErrorException();
        }
    }


}
