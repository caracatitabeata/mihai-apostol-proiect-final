package org.mihaiaposotlproiectfinal.entities;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    @NotEmpty(message = "{javax.validation.constraints.NotEmpty.message}")
    @Email
    private String email;

    @NotNull
    private String password;

}
