package org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories;

import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setClientId(rs.getLong("clientId"));
        client.setClientName(rs.getString("clientName"));
        client.setEmailAddress(rs.getString("emailAddress"));
        client.setPhoneNumber(rs.getString("phoneNumber"));
        client.setPassword(rs.getString("password"));
        return client;
    }
}
