package com.bankapp.exception;

public class ChequeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ChequeNotFoundException(String message) {
		super(message);
	}

}
