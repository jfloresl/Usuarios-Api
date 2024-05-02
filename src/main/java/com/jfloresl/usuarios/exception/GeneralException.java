package com.jfloresl.usuarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jfloresl.usuarios.handler.ResponseHandler;
import com.jfloresl.usuarios.utils.Constantes;

@ControllerAdvice
public class GeneralException {
	
	@ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    public ResponseEntity<Object> missionException() {
		return ResponseHandler.generateResponse(Constantes.missingError, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(com.fasterxml.jackson.core.JsonParseException.class)
    public ResponseEntity<Object> JsonException() {
		return ResponseHandler.generateResponse(Constantes.jsonError, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> methodException() {
		return ResponseHandler.generateResponse(Constantes.serviceNotFound, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageException() {
		return ResponseHandler.generateResponse(Constantes.jsonError, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerException() {
		return ResponseHandler.generateResponse(Constantes.jsonError, HttpStatus.BAD_REQUEST);
    }
	
}
