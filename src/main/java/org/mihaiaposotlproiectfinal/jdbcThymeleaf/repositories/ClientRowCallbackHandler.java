package org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories;

import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ClientRowCallbackHandler implements RowCallbackHandler {

    private final Map<Long, Client> customerMap;

    public ClientRowCallbackHandler(Map<Long, Client> customerMap) { this.customerMap = customerMap;}

    public void processRow(ResultSet rs) throws SQLException {
        Long id = rs.getLong("clientId");
        Client client = customerMap.get(id);
        if(client == null){
            client = new Client();
            client.setClientId(id);
            client.setClientName(rs.getString("clientName"));
            //client.setEmailAddress(rs.getString("emailAddress"));
            //client.setPhoneNumber(rs.getString("phoneNumber"));
            //client.setPassword(rs.getString("password"));
            customerMap.put(id, client);
        }
    }
}

