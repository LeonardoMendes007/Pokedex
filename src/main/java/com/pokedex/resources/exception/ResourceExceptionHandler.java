package com.pokedex.resources.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pokedex.service.exception.DatabaseConstraintViolationException;
import com.pokedex.service.exception.DatabaseException;
import com.pokedex.service.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String info = "resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(Instant.now(), status.value(), info, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> dataBaseError(DatabaseException e, HttpServletRequest request){
		String info = "internal error";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError error = new StandardError(Instant.now(), status.value(), info, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DatabaseConstraintViolationException.class)
	private ResponseEntity<StandardError> constraintError(DatabaseConstraintViolationException e, HttpServletRequest request) {
		String info = "integrity error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError(Instant.now(), status.value(), info, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);

	}
}
