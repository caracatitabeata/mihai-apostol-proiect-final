package org.mihaiaposotlproiectfinal.controller;


import org.mihaiaposotlproiectfinal.entities.HibernateProduct;
import org.mihaiaposotlproiectfinal.hibernateRepositories.ClientRepository;
import org.mihaiaposotlproiectfinal.hibernateRepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HibernateProductController {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private ClientRepository clientRepository;

        @GetMapping("/clients/{clientId}/products")
        public Page<HibernateProduct> getAllHibernateProductsByClientId(@PathVariable (value = "clientId") Long clientId,
                                                    Pageable pageable) {
            return productRepository.findByClientId(clientId, pageable);
        }

        @PostMapping("/clients/{clientId}/products")
        public HibernateProduct createHibernateProduct(@PathVariable (value = "clientId") Long clientId,
                                     @Valid @RequestBody HibernateProduct product) {
            return clientRepository.findById(clientId).map(client -> {
                product.setClient(client); //setez hibernateClient
                return productRepository.save(product);
            }).orElseThrow(() -> new RuntimeException("ClientId " + clientId + " not found"));
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
