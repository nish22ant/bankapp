package com.bankapp.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepositDTO {
	@NotNull(message = "Account Number cannot be empty")
	@Min(value = 1, message = "Account Number must be greater than zero")
	private long accountNumber;

	@NotBlank
	@NotNull(message = "Cheque Number cannot be empty")
	private String chequeNumber;

	@NotBlank
	@NotNull(message = "Account Password cannot be empty")
	@NotNull
	private String accountPassword;

	@Min(value = 1, message = "Amount must be greater than zero")
	private double amount;

	@NotNull(message = "Please select a file")
	private MultipartFile selectedFile;
}
