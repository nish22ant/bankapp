package com.bankapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.dto.TransferForm;

@RestController
public class TransferController {
	
	@PostMapping("/api/transfer")
	public ResponseEntity<String> transfer(@RequestParam TransferForm transferForm) {
		
		return ResponseEntity.ok("Transfer Success");
	}
}
