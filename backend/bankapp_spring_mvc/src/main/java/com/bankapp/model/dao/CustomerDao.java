package com.bankapp.model.dao;

import java.sql.Connection;
import java.util.List;

import com.bankapp.model.entity.Customer;

public interface CustomerDao {
	public Connection getDaoConnection();
	public Customer selectCustomer(long customerId);
	public boolean insertCustomer(Customer customer);
	public int updateCustomer(Customer customer);
	public int deleteCustomer(long customerId);
	public List<Customer> selectAllCustomer();
}
