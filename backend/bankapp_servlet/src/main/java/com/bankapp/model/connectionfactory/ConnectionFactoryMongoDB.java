package com.bankapp.model.connectionfactory;

import java.util.ResourceBundle;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConnectionFactoryMongoDB {
	private static MongoClient mongoClient = null;
	
	public static MongoClient getClient() {
		ResourceBundle bundle = ResourceBundle.getBundle("accountMongoDB");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		String host = bundle.getString("host");
		String port = bundle.getString("port");
		String database = bundle.getString("database");
		if(mongoClient == null) {
			String uriString = "mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database;
			mongoClient = MongoClients.create(uriString);
			System.out.println("connection successfull");
		}

		return mongoClient;
	}
}
