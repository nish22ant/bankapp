package com.bankapp.controller;

import java.sql.Connection;
import java.sql.SQLException;

import com.bankapp.model.connectionfactory.ConnectionFactoryMongoDB;
import com.bankapp.model.connectionfactory.ConnectionFactoryMySQL;
import com.bankapp.model.dao.CustomerDao;
import com.bankapp.model.dao.CustomerDaoJDBC;
import com.bankapp.model.dto.Customer;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Main {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {

		MongoDatabase database = ConnectionFactoryMongoDB.getDatabase();
		MongoIterable<String> collections = database.listCollectionNames();
		for(String collection: collections) {
			System.out.println(collection);
		}
		
	}

}
