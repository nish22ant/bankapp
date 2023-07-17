package com.bankapp.model.service;

import com.bankapp.model.dto.CustomerDTO;

public interface CustomerService {
	public CustomerDTO getCustomer(long customerId);
	
}
