package com.jfloresl.usuarios.service;

import com.jfloresl.usuarios.repository.PhoneRepository;
import com.jfloresl.usuarios.repository.UserRepository;
import com.jfloresl.usuarios.security.JwtUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    JwtUtil jwtUtil;
    UserRepository userRepository;
    PhoneRepository phoneRepository;

    @BeforeAll
    public static void inicioTest(){
        System.out.println("Inicio de test unitario");
    }
    @AfterAll
    public static void finalTest(){
        System.out.println("Fin de los test");
    }
    @BeforeEach
    public void instanciarObjeto(){
        userService = new UserService(userRepository,phoneRepository,jwtUtil);
    }
    @Test
    @DisplayName("test para comprobar contraseñas de formato valido")
    void checkPasswordFormatTest() {
        assertTrue(userService.checkPasswordFormat("hunterA22"));
        assertTrue(userService.checkPasswordFormat("P@ssw0rd1"));
        assertTrue(userService.checkPasswordFormat("C0mpl3xP@ss"));
    }

    @Test
    @DisplayName("test para comprobar contraseñas de formato invalido")
    void checkIncorrectPasswordFormatTest(){
        assertFalse(userService.checkPasswordFormat("12313"));
        assertFalse(userService.checkPasswordFormat(""));
        assertFalse(userService.checkPasswordFormat("111sadasdasdasdas342"));
    }

    @Test
    @DisplayName("test para comprobar emails de formato valido")
    void checkCorrrectEmailFormatTest(){
        assertTrue(userService.emailFormat("dsfsdf@dominio.cl"));
        assertTrue(userService.emailFormat("aoieurwhrjkhweuikrhikwehjkrhew@dominio.cl"));
        assertTrue(userService.emailFormat("5@dominio.cl"));
    }

    @Test
    @DisplayName("test para comprobar emails de formato invalido")
    void checkIncorrrectEmailFormatTest(){
        assertFalse(userService.emailFormat("dsfsdf@dominio.com"));
        assertFalse(userService.emailFormat("aoieurjkrhew@dominio.c"));
        assertFalse(userService.emailFormat("@dominio.cl"));
    }



}