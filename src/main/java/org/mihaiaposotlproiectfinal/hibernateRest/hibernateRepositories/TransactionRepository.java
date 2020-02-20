package org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories;

import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<HibernateTransaction, Long> {
    HibernateTransaction findByClientId(Long clientId);
}
