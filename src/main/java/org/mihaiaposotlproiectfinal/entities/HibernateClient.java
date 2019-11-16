package org.mihaiaposotlproiectfinal.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

    @Entity
    @Table(name = "clientsHibernate")
    @Data
    public class HibernateClient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Size(max = 100)
        @Column(unique = true) //Ensures client names are unique
        private String name;

        @Size(max = 250)
        private String phoneNumber;

        @NotNull
        @Size(max = 250)
        private String emailAddress;

        @NotNull
        @Size(max = 250)
        private String password;

        // Getters and Setters (Omitted for brevity)
    }
