package com.bankapp.model.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.bankapp.exception.ChequeAlreadyExistsException;
import com.bankapp.exception.ChequeNotFoundException;
import com.bankapp.model.connectionfactory.ConnectionFactoryMongoDB;
import com.bankapp.model.dto.ECheque;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class EChequeDaoImp implements EChequeDao {

	private static final String E_CHEQUE_NOT_PRESENT_IN_DATABASE = "eCheque not present in Database";
	private static final String COLLECTION_NAME = "eCheques";
	private static final String DATABASE_NAME = "bankapp";
	private MongoDatabase mongoDatabase = null;
	private MongoClient mongoClient = null;
	private MongoCollection<Document> collection = null;

	public EChequeDaoImp() {
		this.mongoClient = ConnectionFactoryMongoDB.getClient();
		this.mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
		this.collection = this.mongoDatabase.getCollection(COLLECTION_NAME);
	}

	@Override
	public List<ECheque> selectAllCheques() {
		List<ECheque> cheques = new ArrayList<>();
		for (Document document : collection.find()) {
			cheques.add(new ECheque(document.getLong("chequeId"), (byte[]) document.get("chequeImage"),
					document.getDate("depositDate")));
		}

		return cheques;
	}

	@Override
	public ECheque selectCheque(long chequeId) {
		Document document = collection.find(eq("chequeId", chequeId)).first();
		if (document != null) {
			return new ECheque(document.getLong("chequeId"), (byte[]) document.get("chequeImage"),
					document.getDate("depositDate"));
		}
		return null;
	}

	@Override
	public boolean insertCheque(ECheque eCheque) {
		ClientSession session = mongoClient.startSession();
		try {
			session.startTransaction();
			Document existingCheque = collection.find(eq("id", eCheque.getChequeId())).first();
			if (existingCheque != null) {
				throw new ChequeAlreadyExistsException(
						"Cheque with ID " + eCheque.getChequeId() + " already exists in the collection.");
			}
			Document document = new Document("chequeId", eCheque.getChequeId())
					.append("chequeImage", eCheque.getChequeImage()).append("date", eCheque.getChequeDepositDate());
			collection.insertOne(session, document);
			session.commitTransaction();
			return true;
		} catch (Exception e) {
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
			Document existingCheque = collection.find(eq("id", chequeId)).first();
			if (existingCheque == null) {
				throw new ChequeNotFoundException(E_CHEQUE_NOT_PRESENT_IN_DATABASE);
			}
			Document filter = new Document("chequeId", chequeId);
			collection.deleteOne(session, filter);
			session.commitTransaction();
			return 1;
			
		} catch (Exception e) {
			session.abortTransaction();
			// TODO: handle exception
		} finally {
			session.close();
		}
		return 0;
	}

}
