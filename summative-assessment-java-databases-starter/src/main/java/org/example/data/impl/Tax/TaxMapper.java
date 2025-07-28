package org.example.data.impl.Tax;

import org.example.model.Tax;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class TaxMapper implements RowMapper<Tax> {


    @Override
    public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tax tax = new Tax();
        tax.setTaxID(rs.getInt("TaxID"));
        tax.setTaxPercentage(rs.getBigDecimal("TaxPercentage"));
        tax.setStartDate(rs.getDate("StartDate").toLocalDate());
        if (rs.getDate("EndDate") != null) {
            tax.setEndDate(rs.getDate("EndDate").toLocalDate());
        }
        return tax;
    }
}
