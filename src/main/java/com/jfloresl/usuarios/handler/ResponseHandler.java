package com.jfloresl.usuarios.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jfloresl.usuarios.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
	
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", String.valueOf((status.value())));
        map.put("message", message);
        map.put("data", responseObj);
        return new ResponseEntity<Object>(map,status);
    }
    
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", String.valueOf((status.value())));
        map.put("message", message);
        return new ResponseEntity<Object>(map,status);
    }

	public static ResponseEntity<Object> generateResponse(User user, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", String.valueOf((status.value())));
        map.put("message", user);
        return new ResponseEntity<Object>(map,status);
	}

	public static ResponseEntity<Object> generateResponse(List<User> lista, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", String.valueOf((status.value())));
        map.put("message", lista);
        return new ResponseEntity<Object>(map,status);
	}



}