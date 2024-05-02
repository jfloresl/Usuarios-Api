package com.jfloresl.usuarios.entities.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    private String email;

    private String password;

}
