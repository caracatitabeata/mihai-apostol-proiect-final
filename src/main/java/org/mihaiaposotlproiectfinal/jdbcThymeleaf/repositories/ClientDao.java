package org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories;

import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;

import java.util.Collection;
import java.util.List;

public interface ClientDao {

    List<Client> getAllClients();
    Collection<Client> getClientsAndProductName();
    void updateClientPhoneNumber(Client client);
    void insertClient(Client client);
    void deleteClientById(Long id);
    Collection<Client> getClientProduct();
    Client findById(Long id);

}
