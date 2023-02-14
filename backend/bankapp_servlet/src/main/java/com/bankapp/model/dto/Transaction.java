package com.bankapp.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class Transaction {
	private long transactionId;
	@NonNull
	private long fromAccountNumber;
	@NonNull
	private long toAccountNumber;
	@NonNull
	private String transactionType;
	@NonNull
	private double transactionAmount;
}
