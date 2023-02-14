package com.bankapp.model.dao;

import java.sql.Connection;
import java.util.List;

import com.bankapp.model.dto.ECheque;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class EChequeDaoImp implements EChequeDao {
	
	MongoClient mongoClient = null;
	DB database = null;
	
	public EChequeDaoImp() {
		
	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ECheque> selectAllCheques() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ECheque selectCheque(long chequeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertCheque(ECheque eCheque) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateCheque(ECheque eCheque) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCheque(long chequeId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
