package org.mihaiaposotlproiectfinal.hibernateRest.hibernateController;


import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateClient;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/hibernateClients")
public class HibernateClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public Page<HibernateClient> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @PostMapping("/clients")
    public HibernateClient createClient(@Valid @RequestBody HibernateClient hibernateClient) {
        return clientRepository.save(hibernateClient);
    }

    @PutMapping("/clients/{clientId}")
    public HibernateClient updateClient(@PathVariable Long clientId, @Valid @RequestBody HibernateClient clientRequest) {
        return clientRepository
                .findById(clientId)
                .map(client -> {
            client.setName(clientRequest.getName());
            client.setEmailAddress(clientRequest.getEmailAddress());
            client.setPassword(clientRequest.getPassword());
            return clientRepository.save(client);
        })
                .orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
    }


    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientRepository.findById(clientId).map(post -> {
            clientRepository.delete(post);
            return ResponseEntity.ok().build(); //nu mi-e clar ce face asta
        }).orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
    }
}
