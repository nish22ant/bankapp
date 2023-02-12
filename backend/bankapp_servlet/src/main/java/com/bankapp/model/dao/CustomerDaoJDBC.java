package com.bankapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bankapp.model.dto.Customer;

public class CustomerDaoJDBC implements CustomerDao {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	private final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customers  WHERE customerId = ?";
	private final String ADD_CUSTOMER = "INSERT INTO customers(customerName, customerAddress, customerMobile, customerEmail) VALUES(?, ?, ?, ?)";
	private final String UPDATE_CUSTOMER = "UPDATE customers SET customerName = ?, customerAddress = ?, customerMobile = ?, customerEmail = ? WHERE customerId = ?";
	private final String DELETE_CUSTOMER = "DELETE FROM accounts WHERE customerId = ?";
	private final String SELECT_ALL_CUSTOMER = "SELECT * FROM customers";

	public CustomerDaoJDBC(Connection connection) {
		this.connection = connection;
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getDaoConnection() {
		return connection;
	}


	@Override
	public Customer selectCustomer(long customerId) {
		Customer customer = null;
		try {
			preparedStatement = this.connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
			preparedStatement.setLong(1, customerId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String customerName = rs.getString(2);
				String customerAddress = rs.getString(3);
				String customerMobile = rs.getString(4);
				String customerEmail = rs.getString(5);
				customer = new Customer(customerId, customerName, customerAddress, customerMobile, customerEmail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		try {
			preparedStatement = this.connection.prepareStatement(ADD_CUSTOMER);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerAddress());
			preparedStatement.setString(3, customer.getCustomerMobile());
			preparedStatement.setString(4, customer.getCustomerEmail());
			return preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public int updateCustomer(Customer customer) {
		try {
			preparedStatement = this.connection.prepareStatement(UPDATE_CUSTOMER);
			preparedStatement.setString(1, customer.getCustomerName());
			preparedStatement.setString(2, customer.getCustomerAddress());
			preparedStatement.setString(3, customer.getCustomerMobile());
			preparedStatement.setString(4, customer.getCustomerEmail());
			preparedStatement.setLong(5, customer.getCustomerId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteCustomer(long customerId) {
		try {
			preparedStatement = this.connection.prepareStatement(DELETE_CUSTOMER);
			preparedStatement.setLong(1, customerId);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public List<Customer> selectAllCustomer() {
		List<Customer> customers = new ArrayList<>();
		try {
			preparedStatement = this.connection.prepareStatement(SELECT_ALL_CUSTOMER);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long customerId = rs.getLong(1);
				String customerName = rs.getString(2);
				String customerAddress = rs.getString(3);
				String customerMobile = rs.getString(4);
				String customerEmail = rs.getString(5);
				customers.add(new Customer(customerId, customerName, customerAddress, customerMobile, customerEmail));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

}
