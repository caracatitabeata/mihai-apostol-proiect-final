package org.mihaiaposotlproiectfinal.hibernateRest.hibernateRepositories;

import org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities.HibernateClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<HibernateClient, Long> {

}
