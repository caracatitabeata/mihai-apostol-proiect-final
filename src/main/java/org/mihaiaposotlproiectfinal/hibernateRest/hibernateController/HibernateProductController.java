package org.mihaiaposotlproiectfinal.hibernateRest.hibernateController;


import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateProduct;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateTransaction;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ClientRepository;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.ProductRepository;
import org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//cel mai important controller! - productd
@RestController
public class HibernateProductController {

        @Autowired
        private ProductRepository productRepository;
        @Autowired
        private ClientRepository clientRepository;
        @Autowired
        private TransactionRepository transactionRepository;

        @GetMapping("/clients/{clientId}/products")
        public Page<HibernateProduct> getAllHibernateProductsByClientId(@PathVariable (value = "clientId") Long clientId,
                                                    Pageable pageable) {
            return productRepository.findByClientId(clientId, pageable);
        }

        //un client poate avea mai multe produse, deci nu trebuie sa verificam daca un client are deja un produs
        @PostMapping("/clients/{clientId}/products")
        public HibernateProduct createHibernateProduct(@PathVariable (value = "clientId") Long clientId,
                                     @Valid @RequestBody HibernateProduct product) {
            return clientRepository.findById(clientId).map(client -> {
                product.setClient(client); //setez hibernateClient
                return productRepository.save(product);
            }).orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
        }

    @PostMapping("/clients/{clientId}/transactions/{transactionId}/products")
    public HibernateProduct createAnotherHibernateProduct(@PathVariable (value = "clientId") Long clientId,
                                                   @PathVariable (value = "transactionId") Long transactionId,
                                                   @Valid @RequestBody HibernateProduct product) {
            if (transactionRepository.existsById(transactionId)){
         transactionRepository.findById(transactionId).map(transaction-> {
                 product.setTransaction(transaction);
                 //return productRepository.save(product);
             return clientRepository
                     .findById(clientId)
                     .map(client -> {
                 product.setClient(client); //setez hibernateClient
                 return productRepository.save(product);
             })
                     .orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
         });
            }
            else throw new RuntimeException("TransactionId" + transactionId + "not found");
            return product;
    }

        @PutMapping("/clients/{clientId}/products/{productId}")
        public HibernateProduct updateHibernateProduct(@PathVariable (value = "clientId") Long clientId,
                                     @PathVariable (value = "productId") Long productId,
                                     @Valid @RequestBody HibernateProduct productRequest) {
            if(!clientRepository.existsById(clientId)) {
                throw new RuntimeException("ClientId " + clientId + " not found");
            }

            return productRepository.findById(productId).map(product -> {
                product.setName(productRequest.getName());
                return productRepository.save(product);
            }).orElseThrow(() -> new RuntimeException("HibernateProductId " + productId + "not found"));
        }

        @DeleteMapping("/clients/{clientId}/products/{productId}")
        public ResponseEntity<?> deleteHibernateProduct(@PathVariable (value = "clientId") Long clientId,
                                               @PathVariable (value = "productId") Long productId) {
            return productRepository.findByClientIdAndId(clientId, productId).map(product -> {
                productRepository.delete(product);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new RuntimeException("HibernateProduct not found with id " + productId + " and clientId " + clientId));
        }
    }
