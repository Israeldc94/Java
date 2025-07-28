package org.example.data.impl.Server;

import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ServerMapper implements RowMapper<Server> {


    @Override
    public Server mapRow(ResultSet rs, int rowNum) throws SQLException {
        Server server = new Server();
        server.setServerID(rs.getInt("ServerID"));
        server.setFirstName(rs.getString("FirstName"));
        server.setLastName(rs.getString("LastName"));
        server.setHireDate(rs.getDate("HireDate").toLocalDate());
        if (rs.getDate("TermDate") != null) {
            server.setTermDate(rs.getDate("TermDate").toLocalDate());
        }
        return server;

    }
}
