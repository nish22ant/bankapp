package com.bankapp.model.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactoryMySQL {
	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ResourceBundle bundle = ResourceBundle.getBundle("accountMySQL");
			String url = bundle.getString("url");
			String user = bundle.getString("user");
			String password = bundle.getString("password");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection Successful!");
		}
		return connection;
	}
}
