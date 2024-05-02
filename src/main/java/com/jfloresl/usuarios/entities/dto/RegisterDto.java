package com.jfloresl.usuarios.entities.dto;

import com.jfloresl.usuarios.entities.Phone;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Hidden
@Data
public class RegisterDto implements Serializable {
    private String name;
    private String email;
    private String password;
    private List<Phone> phones = new ArrayList<>();

}
