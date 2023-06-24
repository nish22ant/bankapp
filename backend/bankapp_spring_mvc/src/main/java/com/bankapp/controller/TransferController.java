package com.bankapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.dto.TransferDTO;
import com.bankapp.model.service.AccountService;

@RestController
public class TransferController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/api/transfer")
	public ResponseEntity<?> transfer(@Valid @ModelAttribute TransferDTO transferFormDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
				System.out.println(error.getDefaultMessage());
			}
			
			JSONObject responseJson = new JSONObject();
            responseJson.put("message", "Validation failed");

            JSONArray errorsJson = new JSONArray(errors);
            responseJson.put("errors", errorsJson);
			return ResponseEntity.badRequest().body(errors);
		}

		try {
			accountService.transfer(transferFormDTO.getFromAccountNumber(), transferFormDTO.getToAccountNumber(),
					transferFormDTO.getAmount(), transferFormDTO.getAccountPassword());
			return ResponseEntity.ok("Transfer successful");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transfer failed");
		}
	}
}
