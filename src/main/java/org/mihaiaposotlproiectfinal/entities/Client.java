package org.mihaiaposotlproiectfinal.entities;

import lombok.Data;

@Data
public class Client {

    private Long clientId;
    private String clientName;
    private String phoneNumber;
    private String emailAddress;
    private String password;

}
