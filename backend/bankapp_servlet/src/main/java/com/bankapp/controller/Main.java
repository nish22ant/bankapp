package com.bankapp.controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.bankapp.model.dao.ConnectionFactory;
import com.bankapp.model.dao.CustomerDao;
import com.bankapp.model.dao.CustomerDaoJDBC;
import com.bankapp.model.dto.Customer;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

}
