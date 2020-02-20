package org.mihaiaposotlproiectfinal.jdbcThymeleaf.service;

import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories.ClientDao;

import java.util.Collection;
import java.util.List;

public interface ClientService {

     //ClientDao getClientDao();

     List<Client> getAllClients();
     void insertClient(Client client) throws RuntimeException;
     void deleteClientById(Long clientId);
     Collection<Client> getClientsAndProductName();
     Client findById(Long id);// de ce nu găsește?
}
