package org.mihaiaposotlproiectfinal.jdbcThymeleaf.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Client {

    private Long clientId;
    private String clientName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

    //nu faci coloana products in tabela client in MySQL, dar fiecare obiect Client o sa aiba unul
    private List<Product> products;

    public Client(Long clientId, String clientName){
        this.clientId = clientId;
        this.clientName = clientName;
    }
}
