package com.bankapp.model.connectionfactory;

import java.util.ResourceBundle;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class ConnectionFactoryMongoDB {
	private static MongoClient mongoClient = null;
	private static MongoDatabase mongoDatabase = null;
	
	public static MongoDatabase getDatabase() {
		ResourceBundle bundle = ResourceBundle.getBundle("accountMongoDB");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		String host = bundle.getString("host");
		String port = bundle.getString("port");
		String database = bundle.getString("database");
		if(mongoClient == null) {
			MongoClientURI uri = new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database);
			mongoClient = new MongoClient(uri);
			mongoDatabase = mongoClient.getDatabase(database);
			System.out.println("connection successfull");
		}
		return mongoDatabase;
	}
}
