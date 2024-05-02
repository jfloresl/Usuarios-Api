package com.jfloresl.usuarios.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;

    @BeforeEach
    public void instanciarObjeto(){
        userService = new UserService();
    }
    @Test
    void checkPasswordFormatTest() {
        assertTrue(userService.checkPasswordFormat("hunterA22"));
        assertTrue(userService.checkPasswordFormat("P@ssw0rd1"));
        assertTrue(userService.checkPasswordFormat("C0mpl3xP@ss"));
    }

    @Test
    void checkIncorrectPasswordFormatTest(){
        assertFalse(userService.checkPasswordFormat("12313"));
        assertFalse(userService.checkPasswordFormat(""));
        assertFalse(userService.checkPasswordFormat("111sadasdasdasdas342"));
    }
}