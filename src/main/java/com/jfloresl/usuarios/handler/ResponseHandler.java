package com.jfloresl.usuarios.handler;

import com.jfloresl.usuarios.entities.dto.RespDto;
import com.jfloresl.usuarios.entities.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

	public static ResponseEntity<Object> generateResponse(RespDto user, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", String.valueOf((status.value())));
        map.put("message", user);
        return new ResponseEntity<Object>(map,status);
	}

	public static ResponseEntity<Object> generateResponse(List<UserDto> lista, HttpStatus status) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", String.valueOf((status.value())));
        map.put("message", lista);
        return new ResponseEntity<Object>(map,status);
	}



}