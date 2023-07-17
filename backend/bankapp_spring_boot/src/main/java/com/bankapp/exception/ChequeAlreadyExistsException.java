package com.bankapp.exception;

public class ChequeAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ChequeAlreadyExistsException(String message) {
		super(message);
	}

}
