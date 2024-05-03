package com.jfloresl.usuarios.entities.dto;

import com.jfloresl.usuarios.entities.Phone;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class UserDto implements Serializable {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDate modified;
    private LocalDate last_login;
    private String token;
    private String isActive;
    private List<Phone> phones;
}
