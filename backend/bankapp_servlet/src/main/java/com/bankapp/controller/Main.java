package com.bankapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.bankapp.model.connectionfactory.ConnectionFactoryMongoDB;
import com.bankapp.model.dao.EChequeDao;
import com.bankapp.model.dao.EChequeDaoImp;
import com.bankapp.model.dto.ECheque;
import com.mongodb.client.MongoClient;

public class Main {

	public static void main(String[] args) throws IOException {

		MongoClient mongoClient = ConnectionFactoryMongoDB.getClient();
		EChequeDao eChequeDao = new EChequeDaoImp(mongoClient);
//		List<ECheque> eCheques = eChequeDao.selectAllCheques();
//		System.out.println("Size: " + eCheques.size());
//		for(ECheque eCheque: eCheques) {
//			System.out.println("Echequessss: " + eCheque);
//		}
		System.out.println(eChequeDao.selectCheque(1));
		System.out.println("Done");
		String imagePath = "src/main/java/com/bankapp/controller/nebula.jpg";
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
//        MongoCollection<Document> collection =eChequeDao.getCollection();
//        Document document = new Document("chequeId", 1)
//        System.out.println("Reached");
//        System.out.println("Reached");
//        System.out.println("Reached");
        boolean bool = eChequeDao.insertCheque(new ECheque(3, imageBytes, new Date()));
        System.out.println(bool ? "Inserted" : "Not Inserted");
        int result = eChequeDao.deleteCheque(3);
        System.out.println(result > 0 ? "Deleted" : "Not Deleted");
//        System.out.println("Completed");
//        System.out.println(eChequeDao.selectCheque(1));
//        System.out.println(eChequeDao.selectCheque(2));
	}

}
