package com.bankapp.model.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.bankapp.exception.CustomerNotFoundException;
import com.bankapp.model.dao.CustomerDAO;
import com.bankapp.model.dto.CustomerDTO;
import com.bankapp.model.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDao;
	
	private static final String CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE = "Customer Not Found!";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerDTO getCustomer(long customerId) {
		Optional<Customer> optionalCustomer = customerDao.findById(customerId);
		if(optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		Customer customer = optionalCustomer.get();
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}
	
}
