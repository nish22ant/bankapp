package com.bankapp.model.dao;

import java.util.List;

import org.bson.Document;

import com.bankapp.model.dto.ECheque;
import com.mongodb.client.MongoCollection;

public interface EChequeDao {
	public MongoCollection<Document> getCollection();
	public List<ECheque> selectAllCheques();
	public ECheque selectCheque(long chequeId);
	public boolean insertCheque(ECheque eCheque);
	public int updateCheque(ECheque eCheque);
	public int deleteCheque(long chequeId);
	
}
