package org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity (name = "transactionshibernate")
@Table (name = "transactionshibernate")
@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HibernateTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //by ddefault optional = true
    @OneToOne(fetch = FetchType.LAZY, optional = false, mappedBy = "transaction")
    @JsonIgnore
    HibernateClient client;
}
