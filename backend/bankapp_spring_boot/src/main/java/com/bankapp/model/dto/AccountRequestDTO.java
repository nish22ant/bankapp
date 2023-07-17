package com.bankapp.model.dto;

import com.bankapp.model.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountRequestDTO {
	private Customer customer;	
	private String accountPassword;
	private double accountBalance;
}
