package com.jfloresl.usuarios.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.handler.ResponseHandler;
import com.jfloresl.usuarios.service.UserService;
import com.jfloresl.usuarios.utils.Constantes;

@Hidden
@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping({"all","all/"})
    public ResponseEntity<Object> findAll(@RequestBody Map<String, String> request){
        return userService.findAll(request);
    }

	@PostMapping({"find","find/"})
    public ResponseEntity<Object> findOneByIdPost(@RequestBody Map<String, String> request){
		return userService.findById(request);
    }
		
	//crear
	@PostMapping({"create","create/"})
	public ResponseEntity<Object> createUser(@RequestBody User user) {		
		return userService.createdUser(user);
	}

	//borrar
	@DeleteMapping({"delete","delete/"})
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, String> request){
		return userService.deleteById(request);
    }

	//modificar
	@PutMapping({"edit","edit/"})
	public ResponseEntity<Object> updateUser(@RequestBody User user) {		
		return userService.updateUser(user);
	}
	
	//default
	@RequestMapping(value = {"*/*","*"}, method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
	public ResponseEntity<Object> notMappingUrl(HttpServletResponse response) {
		return ResponseHandler.generateResponse(Constantes.serviceNotFound, HttpStatus.NOT_FOUND);
	}
	
}