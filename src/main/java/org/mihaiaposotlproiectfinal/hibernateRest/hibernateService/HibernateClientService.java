package org.mihaiaposotlproiectfinal.hibernateRest.hibernateService;

import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateClient;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class HibernateClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<HibernateClient> getAllClients() {
        return clientRepository.findAll();
    }

    public HibernateClient saveClient(HibernateClient hibernateClient) {
        return clientRepository.save(hibernateClient);
    }

    public Optional<HibernateClient> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    public void deleteClient(HibernateClient hibernateClient) {
        clientRepository.delete(hibernateClient);
    }
}
