package org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

    @Entity
    @Table(name = "clientshibernate")
    @Data
    public class HibernateClient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Size(max = 100)
        @Column(unique = true) //Ensures client names are unique (if Hibernate creates the table definition?)
        private String name;

        @Size(max = 250)
        private String phoneNumber;

        @NotNull
        @Size(max = 250)
        private String emailAddress;

        @NotNull
        @Size(max = 250)
        private String password;

        //The owning side has this FK.
        @OneToOne (fetch = FetchType.LAZY, optional = true)
        @JoinColumn(name = "transaction_id") //inseamna ca tine cont de id, din transaction
        @JsonIgnore
        private HibernateTransaction transaction;

        @NotNull
        private Long userId;
    }
