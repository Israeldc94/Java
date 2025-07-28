package org.example.data.impl.Order;

import org.example.model.Order;
import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public static Order mapOrder(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setOrderID(rs.getInt("OrderID"));
        order.setServerID(rs.getInt("ServerID"));
        order.setOrderDate(rs.getObject("OrderDate", LocalDateTime.class));
        order.setSubTotal(rs.getBigDecimal("SubTotal"));
        order.setTax(rs.getBigDecimal("Tax"));
        order.setTip(rs.getBigDecimal("Tip"));
        order.setTotal(rs.getBigDecimal("Total"));

        return order;
    }

    public static Server mapServer(ResultSet rs) throws SQLException {
        Server server = new Server();

        server.setFirstName(rs.getString("FirstName"));
        server.setLastName(rs.getString("LastName"));
        server.setHireDate(rs.getDate("HireDate").toLocalDate());

        if (rs.getDate("TermDate") != null) {
            server.setTermDate(rs.getDate("TermDate").toLocalDate());
        }

        return server;
    }
}