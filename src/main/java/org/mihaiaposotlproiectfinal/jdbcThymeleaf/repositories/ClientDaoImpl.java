package org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories;

import lombok.Getter;
import lombok.Setter;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Getter
@Setter
public class ClientDaoImpl implements ClientDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; //de aflat

    public ClientDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM clients";
        return namedParameterJdbcTemplate.query(sql, new ClientMapper()); //sa mai folosesc client mapper la many to many?
    }

    @Override
    public Client findById(Long id){
        String sql = "SELECT * FROM clients WHERE clientId = :id";
        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource("clientId", id), new ClientMapper());
    }


    public List<Client> getClientProduct() {
        Map<Long, Client> customerMap = new HashMap<>();
        String sql = "select clients.clientId, clients.clientName from clients ";
        String sql2 = "select products.clientId, products.productId, products.productName from products";
        namedParameterJdbcTemplate.query(sql, new ClientRowCallbackHandler(customerMap));
        namedParameterJdbcTemplate.query(sql2, new ProductRowCallbackHandler(customerMap));
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Collection<Client> getClientsAndProductName(){
        final Map<Long, Client> clientMap = namedParameterJdbcTemplate.query(
                "select clients.clientId, clients.clientName, products.productName from clients "
                + "left join products on clients.clientId = products.clientId",
                new ResultSetExtractor<Map<Long, Client>>() {
            public Map<Long, Client> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Long, Client> clientMap = new HashMap<>();
                while(rs.next()) {
                    Long clientId = rs.getLong("clientId");
                    Client client = clientMap.get(clientId);
                    if (client == null) {
                        client = new Client();
                        client.setClientId(clientId);
                        client.setClientName(rs.getString("clientName"));
                        clientMap.put(clientId, client);
                    }
                    List<Product> productList= client.getProducts();
                    if (productList == null){
                        productList = new ArrayList<>();
                        client.setProducts(productList);
                    }
                    Product product = new Product();
                    product.setProductName(rs.getString("productName"));
                    productList.add(product);
                }
                return clientMap;
            }
        });
        return clientMap.values();
    }

    @Override
    public void insertClient(Client client) {
        final String Sql = "INSERT INTO clients (clientName, phoneNumber, emailAddress, password) " +
                "value(:clientName, :password, :phoneNumber, :emailAddress)";
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
    public void deleteClientById(Long clientId){
        final String sql = "DELETE FROM clients WHERE clientId = :clientId" ;
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("clientId", clientId));
        }
    }
