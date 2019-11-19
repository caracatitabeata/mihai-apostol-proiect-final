package org.mihaiaposotlproiectfinal.hibernateRest.hibernateEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productsHibernate")
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
    //has to have a client associated with it (he can be the same)
    @OnDelete(action = OnDeleteAction.CASCADE) //deleting the parent hibernateClient will delete
    // all child products associated with it
    @JsonIgnore //nu trimite JSON-ul trimis din Postman
    private HibernateClient client;


}