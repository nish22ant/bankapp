package com.bankapp.controller;

import com.bankapp.model.connectionfactory.ConnectionFactoryMongoDB;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Main {

	public static void main(String[] args) {

		MongoDatabase database = ConnectionFactoryMongoDB.getClient().getDatabase("bankapp");
		MongoIterable<String> collections = database.listCollectionNames();
		for(String collection: collections) {
			System.out.println(collection);
		}
		
	}

}
