package com.bankapp.utils;

import java.util.ArrayList;

public class Messages {
	private ArrayList<String> errors;
	private String fromAccountNumber;
	private String toAccountNumber;
	private String amount;
	private String password;
	private String passwordAgain;

	public Messages(String fromAccountNumber, String toAccountNumber, String amount,
			String password, String passwordAgain) {
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
		this.password = password;
		this.passwordAgain = passwordAgain;
		if (fromAccountNumber == null || toAccountNumber == null || amount == null || password == null
				|| passwordAgain == null)
			errors.add("One or More Parameters are Empty!");
	}
	
	public ArrayList<String> getErrors() {
		return this.errors;
	}
}
