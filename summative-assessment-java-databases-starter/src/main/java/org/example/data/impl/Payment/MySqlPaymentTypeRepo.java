package org.example.data.impl.Payment;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.model.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlPaymentTypeRepo implements PaymentTypeRepo {

    private final JdbcTemplate jdbc;

    @Autowired
    public MySqlPaymentTypeRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<PaymentType> getAll() throws InternalErrorException {
        String sql = "SELECT * FROM PaymentType";
        try {
            return jdbc.query(sql, new PaymentTypeMapper());
        } catch (DataAccessException ex) {
            throw new InternalErrorException(ex);
        }
    }
}
