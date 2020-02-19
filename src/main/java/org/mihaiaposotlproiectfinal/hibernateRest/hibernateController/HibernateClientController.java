package org.mihaiaposotlproiectfinal.hibernateRest.hibernateController;


import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateClient;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ClientRepository;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateService.HibernateClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/hibernateClients")
public class HibernateClientController {

    @Autowired
    private HibernateClientService clientService;

    @GetMapping("/clients")
    public List<HibernateClient> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/clients")
    public HibernateClient createClient(@Valid @RequestBody HibernateClient hibernateClient) {
        return clientService.saveClient(hibernateClient);
    }

    @PutMapping("/clients/{clientId}")
    public HibernateClient updateClient(@PathVariable Long clientId, @Valid @RequestBody HibernateClient clientRequest) {
        return clientService
                .findClientById(clientId)
                .map(client -> {
            client.setName(clientRequest.getName());
            client.setEmailAddress(clientRequest.getEmailAddress());
            client.setPassword(clientRequest.getPassword());
            return clientService.saveClient(client);
        })
                .orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
    }


    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId) {
        return clientService.findClientById(clientId).map(client -> {
            clientService.deleteClient(client);
            return ResponseEntity.ok().build(); //nu mi-e clar ce face asta
        }).orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
    }

//    @GetMapping("/clients/{transactionId}")
//    public HibernateClient getClientByTransactionId(@PathVariable Long transactionId){
//        return clientService.
//    }
}
