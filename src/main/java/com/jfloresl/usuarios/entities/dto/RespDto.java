package com.jfloresl.usuarios.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
@Data
public class RespDto implements Serializable {
    private UUID id;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate last_login;
    private String token;
    private String isActive;

}
