package com.jfloresl.usuarios.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.handler.ResponseHandler;
import com.jfloresl.usuarios.service.UserService;
import com.jfloresl.usuarios.utils.Constantes;

@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping({"all", "all/"})
	@Operation(summary = "Ver todos los usuarios registrados")
	@ApiResponse(responseCode = "200", description = "Todos los usuarios registrados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
	@ApiResponse(responseCode = "400", description = "Formato busqueda invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
	@ApiResponse(responseCode = "403", description = "Token invalido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
	public ResponseEntity<Object> findAll(@RequestBody Map<String, String> request) {
		return userService.findAll(request);
	}
	//default
	@RequestMapping(value = {"*/*","*"}, method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
	@Operation(summary = "Capturar todas las urls no existentes o mal llamadas")
	@ApiResponse(responseCode = "400", description = "Url no existe", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class)))
	ResponseEntity<Object> notMappingUrl(HttpServletResponse response) {
		return ResponseHandler.generateResponse(Constantes.serviceNotFound, HttpStatus.NOT_FOUND);
	}
	
}