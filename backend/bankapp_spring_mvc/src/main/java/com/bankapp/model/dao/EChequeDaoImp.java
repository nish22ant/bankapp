package com.bankapp.model.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;

import com.bankapp.exception.ChequeAlreadyExistsException;
import com.bankapp.exception.ChequeNotFoundException;
import com.bankapp.model.entity.ECheque;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class EChequeDaoImp implements EChequeDao {

	private static final String E_CHEQUE_NOT_PRESENT_IN_DATABASE = "eCheque not present in Database";
	private static final String COLLECTION_NAME = "echeques";
	private static final String DATABASE_NAME = "bankapp";
	private MongoDatabase mongoDatabase = null;
	private MongoClient mongoClient = null;
	private MongoCollection<Document> collection = null;

	public EChequeDaoImp(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
		this.mongoDatabase = this.mongoClient.getDatabase(DATABASE_NAME);
		this.collection = this.mongoDatabase.getCollection(COLLECTION_NAME);
	}

	public MongoCollection<Document> getCollection() {
		return collection;
	}

	@Override
	public List<ECheque> selectAllCheques() {
		List<ECheque> cheques = new ArrayList<>();
		for (Document document : this.collection.find()) {
			Binary binaryData = (Binary) document.get("chequeImage");
			cheques.add(new ECheque(document.getInteger("chequeId"), binaryData.getData(),
					document.getDate("depositDate")));
		}

		return cheques;
	}

	@Override
	public ECheque selectCheque(long chequeId) {
		Document document = collection.find(eq("chequeId", chequeId)).first();
		if (document != null) {
			Binary binaryData = (Binary) document.get("chequeImage");
			return new ECheque(document.getInteger("chequeId"), binaryData.getData(), document.getDate("depositDate"));
		}
		return null;
	}

	@Override
	public boolean insertCheque(ECheque eCheque) {
		ClientSession session = mongoClient.startSession();
		try {
			session.startTransaction();
			ECheque existingCheque = selectCheque(eCheque.getChequeId());
			System.out.println("Checking");
			if (existingCheque != null) {
				System.out.println("Existing");
				throw new ChequeAlreadyExistsException(
						"Cheque with ID " + eCheque.getChequeId() + " already exists in the collection.");
			}
			System.out.println("After throws");
			
			Document document = new Document("chequeId", eCheque.getChequeId())
					.append("chequeImage", eCheque.getChequeImage())
					.append("depositDate", eCheque.getChequeDepositDate());
			System.out.println("After creating document");
			System.out.println("Collection: " + session == null);
			collection.insertOne(document);
			System.out.println("After inserting");
			session.commitTransaction();
			System.out.println("After commiting");
			return true;
		} catch (Exception e) {
			System.out.println("Inside exception");
			session.abortTransaction();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public int updateCheque(ECheque eCheque) {
		ClientSession session = mongoClient.startSession();
		ECheque cheque = selectCheque(eCheque.getChequeId());
		if (cheque == null) {
			throw new ChequeNotFoundException(E_CHEQUE_NOT_PRESENT_IN_DATABASE);
		}
		try {
			session.startTransaction();
			Bson filter = Filters.eq("chequeId", eCheque.getChequeId());
			Bson update = Updates.combine(Updates.set("chequeImage", eCheque.getChequeImage()),
					Updates.set("depositDate", eCheque.getChequeDepositDate()));
			session.commitTransaction();
			return (int) collection.updateOne(filter, update).getMatchedCount();

		} catch (Exception e) {
			session.abortTransaction();
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int deleteCheque(long chequeId) {
		ClientSession session = mongoClient.startSession();
		try {
			session.startTransaction();
			Document existingCheque = collection.find(eq("chequeId", chequeId)).first();
			if (existingCheque == null) {
				throw new ChequeNotFoundException(E_CHEQUE_NOT_PRESENT_IN_DATABASE);
			}
			System.out.println("After throws");
			Document filter = new Document("chequeId", chequeId);
			System.out.println("Here");
			collection.deleteOne(filter);
			session.commitTransaction();
			return 1;

		} catch (Exception e) {
			session.abortTransaction();
		} finally {
			session.close();
		}
		return 0;
	}

}
