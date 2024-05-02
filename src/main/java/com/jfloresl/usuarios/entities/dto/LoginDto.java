package com.jfloresl.usuarios.entities.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.io.Serializable;
@Hidden
@Data
public class LoginDto implements Serializable {

    private String email;

    private String password;

}
