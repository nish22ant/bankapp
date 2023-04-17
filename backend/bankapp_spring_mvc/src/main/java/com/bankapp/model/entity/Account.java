package com.bankapp.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Account {
	private long accountNumber;
	@NonNull
	private long customerId;
	@NonNull
	private String accountPassword;
	@NonNull
	private double accountBalance;
	
}
