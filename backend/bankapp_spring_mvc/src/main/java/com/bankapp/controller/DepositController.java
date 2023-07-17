package com.bankapp.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bankapp.model.dto.DepositDTO;
import com.bankapp.model.entity.ChequeDeposit;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.ChequeDepositService;

/**
 * 
 * @author nknis
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class DepositController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private ChequeDepositService chequeDepositService;

	@PostMapping("/api/deposit")
	public ResponseEntity<String> handleDepositFormSubmit(@Valid @ModelAttribute DepositDTO depositFormDTO,
			BindingResult bindingResult) throws IOException {
		long accountNumber = depositFormDTO.getAccountNumber();
		String chequeNumber = depositFormDTO.getChequeNumber();
		String accountPassword = depositFormDTO.getAccountPassword();
		double amount = depositFormDTO.getAmount();
		MultipartFile selectedFile = depositFormDTO.getSelectedFile();
		try {
			chequeDepositService.insertChequeDeposit(
					new ChequeDeposit(chequeNumber, accountNumber, accountPassword, amount, selectedFile.getBytes()));
			LOGGER.info("Cheque Deposit successful");
			return ResponseEntity.ok("Cheque Deposit successful");
		} catch (Exception e) {
			LOGGER.error("Deposit failed", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deposit failed");
		}
	}
}
