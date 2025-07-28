package org.example.service;

import org.example.data.TaxRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.Tax.MySqlTaxRepo;
import org.example.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlTaxRepoTest {

    private TaxRepo repo;

    @BeforeEach
    void setup() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/SimpleBistroTest");
        ds.setUsername("root");
        ds.setPassword("Cerseiwatch304!");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("CALL set_known_good_state()");

        repo = new MySqlTaxRepo(jdbcTemplate);

        }

        @Test
        void shouldReturnTaxForValidDate() throws Exception {
        LocalDate date = LocalDate.of(2022, 8, 1);

        Tax tax = repo.getCurrentTax(date);

        assertNotNull(tax);
        assertEquals(new BigDecimal("6.25"), tax.getTaxPercentage());
        }

        @Test
        void shouldThrowForInvalidDate() {
        LocalDate date = LocalDate.of(1999, 1, 1);

        assertThrows(RecordNotFoundException.class, () -> {
            repo.getCurrentTax(date);
        });
        }

        @Test
        void shouldThrowInternalErrorWhenQueryFails() {
        TaxRepo brokenRepo = new MySqlTaxRepo(new JdbcTemplate());
        assertThrows(InternalErrorException.class, () -> {
            brokenRepo.getCurrentTax(LocalDate.now());
        });
        }
    }

