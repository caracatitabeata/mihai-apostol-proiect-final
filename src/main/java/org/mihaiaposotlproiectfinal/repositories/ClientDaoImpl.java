package org.mihaiaposotlproiectfinal.repositories;

import lombok.Getter;
import lombok.Setter;
import org.mihaiaposotlproiectfinal.entities.Client;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

@Getter
@Setter
public class ClientDaoImpl implements ClientDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; //de aflat

    public ClientDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        System.out.println("starting ClientDaoImpl");
    }

    @Override
    public List<Client> getAllClients() {
        String Sql = "SELECT * FROM clients";
        return namedParameterJdbcTemplate.query(Sql, new ClientMapper());
    }

    @Override
    public void insertClient(Client client) {
        final String Sql = "INSERT INTO clients (clientId, clientName, phoneNumber, emailAddress, password) " +
                "values(:clientName, :password, :phoneNumber, :emailAddress)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("clientName", client.getClientName())
                .addValue("password", client.getPassword())
                .addValue("phoneNumber", client.getPhoneNumber())
                .addValue("emailAddress", client.getEmailAddress());
        namedParameterJdbcTemplate.update(Sql, param, holder);
    }

    @Override
    public void updateClientPhoneNumber(Client client) {
        final String Sql = "UPDATE clients SET phoneNumber=:phoneNumber WHERE Id=:Id";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", client.getClientId())
                .addValue("phoneNumber", client.getPhoneNumber());
        namedParameterJdbcTemplate.update(Sql, param, holder);
    }

    @Override
    public void deleteClientById(Long id){
        final String Sql = "DELETE FROM clients WHERE Id = " + id;
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource();
        namedParameterJdbcTemplate.update(Sql, param, holder);
        }
    }
