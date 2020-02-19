package org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories;

import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateProduct;
import org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<HibernateProduct, Long> {

    Page<HibernateProduct> findByClientId(Long clientId, Pageable pageable); //a page
    Optional<HibernateProduct> findByClientIdAndId(Long clientId, Long productId); //just one. Id being the product's id
    //probably

    @Query(value = "SELECT * FROM productshibernate p WHERE p.name LIKE CONCAT('%', :ending, '%')", nativeQuery = true)
    List<HibernateProduct> findByNameEndingWithAndPopulationLessThan(@Param("ending") String ending);
}
