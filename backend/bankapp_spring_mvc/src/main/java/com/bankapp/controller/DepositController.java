package com.bankapp.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bankapp.model.dao.ChequeDepositDao;
import com.bankapp.model.dto.DepositDTO;
import com.bankapp.model.entity.ChequeDeposit;
import com.bankapp.model.service.AccountService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "*")
public class DepositController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ChequeDepositDao chequeDepositDao;

	@PostMapping("/api/deposit")
	public ResponseEntity<String> handleDepositFormSubmit(@Valid @ModelAttribute DepositDTO depositFormDTO,
			BindingResult bindingResult) throws IOException {
		long accountNumber = depositFormDTO.getAccountNumber();
		String chequeNumber = depositFormDTO.getChequeNumber();
		String accountPassword = depositFormDTO.getAccountPassword();
		double amount = depositFormDTO.getAmount();
		MultipartFile selectedFile = depositFormDTO.getSelectedFile();

		boolean insertChequeDeposit = chequeDepositDao.insertChequeDeposit(
				new ChequeDeposit(chequeNumber, accountNumber, accountPassword, amount, selectedFile.getBytes()));
		System.out.println(chequeDepositDao.selectAllChequeDeposit().toString());
		System.out.println(insertChequeDeposit);

		try {
			chequeDepositDao.insertChequeDeposit(
					new ChequeDeposit(chequeNumber, accountNumber, accountPassword, amount, selectedFile.getBytes()));
			return ResponseEntity.ok("Cheque Deposit successful");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transfer failed");
		}
	}
}
