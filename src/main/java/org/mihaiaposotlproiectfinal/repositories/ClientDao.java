package org.mihaiaposotlproiectfinal.repositories;

import org.mihaiaposotlproiectfinal.entities.Client;
import java.util.List;

public interface ClientDao {

    List<Client> getAllClients();
    void updateClientPhoneNumber(Client client);
    void insertClient(Client client);
    void deleteClientById(Long id);

}
