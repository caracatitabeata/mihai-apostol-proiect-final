package org.mihaiaposotlproiectfinal.hibernateRest.hibernateController;

import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateClient;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateTransaction;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ClientRepository;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ProductRepository;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HibernateTransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/clients/{clientId}/transactions")
    public HibernateTransaction createHibernateTransaction(@PathVariable(value = "clientId") Long clientId,
                                                   @Valid @RequestBody HibernateTransaction transaction) {
        return clientRepository.findById(clientId).map(client -> {
            if (client.getTransaction() == null) {
                //aici trebuie sa verific daca clientul nu mai are deja o tranzactie
                transaction.setClient(client);
                return transactionRepository.save(transaction);
            } else {
                throw new RuntimeException(client.getName() + " is already associated with that transaction");
            }
        }).orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
    }

    @GetMapping("/transactions/clients/{clientId}")
    public HibernateTransaction findByTransactionId(@PathVariable (value = "clientId") Long clientId){
        return transactionRepository.findByClientId(clientId);
    }
}
