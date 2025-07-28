package org.example.service;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.impl.Payment.MySqlPaymentTypeRepo;
import org.example.model.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySqlPaymentTypeRepoTest {

    private PaymentTypeRepo repo;

    @BeforeEach
    void setup() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/SimpleBistroTest");
        ds.setUsername("root");
        ds.setPassword("Cerseiwatch304!");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("CALL set_known_good_state();");

        repo = new MySqlPaymentTypeRepo(jdbcTemplate);
    }

    @Test
    void shouldReturnAllPaymentTypes() throws InternalErrorException {
        List<PaymentType> types = repo.getAll();

        assertNotNull(types);
        assertFalse(types.isEmpty());

        for (PaymentType pt : types) {
            assertNotNull(pt.getPaymentTypeName());
            assertTrue(pt.getPaymentTypeID() > 0);
        }
    }

    @Test
    void shouldReturnCashAsPaymentTypeOne() throws InternalErrorException {
        List<PaymentType> types = repo.getAll();
        PaymentType cashType = types.stream()
                .filter(pt -> pt.getPaymentTypeID() == 1)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Cash payment type not found"));

        assertEquals("Cash", cashType.getPaymentTypeName());
    }
}
