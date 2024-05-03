package com.jfloresl.usuarios.controller;

import com.jfloresl.usuarios.entities.dto.LoginDto;
import com.jfloresl.usuarios.entities.dto.RegisterDto;
import com.jfloresl.usuarios.security.JwtUtil;
import com.jfloresl.usuarios.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Registrar a un usuario")
    @ApiResponse(responseCode = "200", description = "Successful registration", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(responseCode = "400", description = "Formato de usuario invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
    @PostMapping({"/register","/register/"})
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto){
        return userService.registerUser(registerDto);
    }

    @Operation(summary = "login")
    @ApiResponse(responseCode = "200", description = "Successful login", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(responseCode = "400", description = "Formato de login invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
    @PostMapping({"/login","/login/"})
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }
}
