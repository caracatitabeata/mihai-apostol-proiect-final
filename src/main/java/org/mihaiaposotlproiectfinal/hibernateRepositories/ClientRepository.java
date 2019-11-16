package org.mihaiaposotlproiectfinal.hibernateRepositories;

import org.mihaiaposotlproiectfinal.entities.HibernateClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<HibernateClient, Long> {

}
