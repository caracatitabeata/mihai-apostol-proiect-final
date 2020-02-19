package org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productshibernate")
@Data
public class HibernateProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    private Integer size;

    @NotNull
    @Size(max = 250)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //specifies the relationship - many products, one client
    @JoinColumn(name = "client_id", nullable = false) //declares the foreign key column - every product
    //has to have a client associated with it. The name of the clients table column HAS to be id; in SQL,
    //this should column should be named client_id.
    @OnDelete(action = OnDeleteAction.CASCADE) //deleting the parent hibernateClient will delete
    // all child products associated with it
    @JsonIgnore //nu trimite field-ul odată cu JSON-ul trimis din Postman. În controller, ca să creăm un produs
    //căutăm clienul în funcție de id și setăm clientul produsului în funcție de acel id care identifică clientul
    private HibernateClient client;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "transaction_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE) //ștergi o tranzacție, automat ștergi și produsele
    @JsonIgnore
    private HibernateTransaction transaction;

}