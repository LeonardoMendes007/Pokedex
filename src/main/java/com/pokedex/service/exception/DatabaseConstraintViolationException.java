package com.pokedex.service.exception;

public class DatabaseConstraintViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DatabaseConstraintViolationException(String message) {
		super(message);
	}

}
