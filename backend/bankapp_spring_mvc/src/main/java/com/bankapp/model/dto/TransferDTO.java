package com.bankapp.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferDTO {
	@NotNull(message = "From Account Number cannot be empty")
	@Min(value = 1, message = "From Account Number must be greater than zero")
	private long fromAccountNumber;

	@NotNull(message = "To Account Number cannot be empty")
	@Min(value = 1, message = "To Account Number must be greater than zero")
	private long toAccountNumber;

	@NotNull(message = "Amount cannot be empty")
	@Min(value = 1, message = "Amount must be greater than zero")
	private double amount;

	@NotBlank
	@NotNull
	private String accountPassword;

	@NotBlank
	@NotNull
	private String passwordAgain;
}
