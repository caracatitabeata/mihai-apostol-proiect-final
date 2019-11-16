package org.mihaiaposotlproiectfinal.hibernateRepositories;

import org.mihaiaposotlproiectfinal.entities.HibernateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<HibernateProduct, Long> {
    Page<HibernateProduct> findByClientId(Long clientId, Pageable pageable); //a page
    Optional<HibernateProduct> findByClientIdAndId(Long clientId, Long productId); //just one. Id being the product's id
    //probably
}
