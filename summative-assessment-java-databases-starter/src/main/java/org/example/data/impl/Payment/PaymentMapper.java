package org.example.data.impl.Payment;

import org.example.model.Payment;
import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PaymentMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentID(rs.getInt("PaymentID"));
        payment.setOrderID(rs.getInt("OrderID"));
        payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
        payment.setAmount(rs.getBigDecimal("Amount"));

        PaymentType type = new PaymentType();
        type.setPaymentTypeID(rs.getInt("PaymentTypeID"));
        type.setPaymentTypeName(rs.getString("PaymentTypeName"));

        payment.setPaymentType(type);
        return payment;
    }
}
