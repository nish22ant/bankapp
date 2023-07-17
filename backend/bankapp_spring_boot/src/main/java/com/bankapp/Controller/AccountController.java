package com.bankapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.dto.AccountRequestDTO;
import com.bankapp.model.dto.AccountResponseDTO;
import com.bankapp.model.entity.Account;
import com.bankapp.model.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		return ResponseEntity.status(HttpStatus.OK).body(accounts);
	}

	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable("accountNumber") long accountNumber) {
		AccountResponseDTO accountResponseDTO = accountService.getAccount(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(accountResponseDTO);
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<AccountResponseDTO> saveAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
		AccountResponseDTO accountResponseDTO = accountService.addAccount(accountRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(accountResponseDTO);
	}
}
