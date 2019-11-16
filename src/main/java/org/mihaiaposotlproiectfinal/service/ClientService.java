package org.mihaiaposotlproiectfinal.service;

import lombok.Data;
import org.mihaiaposotlproiectfinal.entities.Client;
import org.mihaiaposotlproiectfinal.repositories.ClientDao;

@Data
public class ClientService {

    private ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void validateName(Client client){
        if (client.getClientName()==null || client.getClientName().length()<=6)
        throw new RuntimeException("Name too short or null");
    }
}
