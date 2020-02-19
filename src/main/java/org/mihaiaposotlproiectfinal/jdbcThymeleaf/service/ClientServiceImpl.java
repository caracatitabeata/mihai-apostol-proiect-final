package org.mihaiaposotlproiectfinal.jdbcThymeleaf.service;

import lombok.Data;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Client;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.repositories.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Data
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    //constructor for bean
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    @Transactional
    public List<Client> getAllClients(){
        // do some business processing here ...
        //now call DAO layer
        return clientDao.getAllClients();
    }

    @Override
    @Transactional
    public Client findById(Long id){
        // do some business processing here ...
        //now call DAO layer
        return clientDao.findById(id);
    }

    @Override
    @Transactional
    public Collection<Client> getClientsAndProductName(){
        // do some business processing here ...
        //now call DAO layer
        return clientDao.getClientsAndProductName();
    }

    @Override
    @Transactional
    public void insertClient(Client client) throws RuntimeException{
        if (client.getClientName()==null || client.getClientName().length()<=6)
            throw new RuntimeException("Name too short or null");
        clientDao.insertClient(client);
    }

    @Override
    @Transactional
    public void deleteClientById(Long clientId){
        // do some business processing here ...
        //now call DAO layer
        clientDao.deleteClientById(clientId);
    }
}
