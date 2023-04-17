package com.bankapp.model.service;

import java.sql.SQLException;
import java.util.List;

import com.bankapp.model.entity.Customer;

public interface CustomerService {
	public List<Customer> getAllCustomers();
	public Customer getCustomer(long customerId);
	public int updateCustomer(Customer customer) throws SQLException;
	public int deleteCustomer(long customerId) throws SQLException;
	public boolean addCustomer(Customer customer) throws SQLException;
}
