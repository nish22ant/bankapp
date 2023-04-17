package com.bankapp.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class TransferForm {
	private long fromAccountNumber;
	private long toAccountNumber;
	private double amount;
	private String password;
	private String passwordAgain;
}
