package com.bankapp.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bankapp.exception.CustomerNotFoundException;
import com.bankapp.model.dao.CustomerDao;
import com.bankapp.model.dto.Customer;

public class CustomerServiceImp implements CustomerService {
	private static final String CUSTOMER_EXCEPTION_MESSAGE = "Customer Not in Database.";
	private CustomerDao customerDao;
	private Connection connection;
	
	public CustomerServiceImp(CustomerDao customerDao) {
		this.customerDao = customerDao;
		this.connection = customerDao.getDaoConnection();
	}
	
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.selectAllCustomer();
	}

	@Override
	public Customer getCustomer(long customerId) {
		Customer customer = customerDao.selectCustomer(customerId);
		if(customer == null) {
			throw new CustomerNotFoundException(CUSTOMER_EXCEPTION_MESSAGE);
		} 
		return customer;
	}

	@Override
	public int updateCustomer(Customer customer) throws SQLException {
		int status = 0;
		try {
			connection.setAutoCommit(false);
			Customer cust = customerDao.selectCustomer(customer.getCustomerId());
			if(cust == null) {
				throw new CustomerNotFoundException(CUSTOMER_EXCEPTION_MESSAGE);
			}
			status = customerDao.updateCustomer(customer);
			connection.commit();
		} catch(SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return status;
	}

	@Override
	public int deleteCustomer(long customerId) throws SQLException {
		int status = 0;
		try {
			connection.setAutoCommit(false);
			Customer customer = customerDao.selectCustomer(customerId);
			if(customer == null) {
				throw new CustomerNotFoundException(CUSTOMER_EXCEPTION_MESSAGE);
			}
			status = customerDao.deleteCustomer(customerId);
			connection.commit();
		} catch(SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(true);
		}
		return status;
	}

	@Override
	public boolean addCustomer(Customer customer) throws SQLException {
		boolean status = false;
		try {
			connection.setAutoCommit(true);
			status = customerDao.insertCustomer(customer);
			connection.commit();
		} catch(SQLException e) {
			connection.rollback();
		} finally {
			connection.setAutoCommit(false);
		}
		return status;
	}

}
