package com.bankapp.model.dao;

import java.sql.Connection;
import java.util.List;

import com.bankapp.model.dto.ECheque;

public interface EChequeDao {
	public Connection getConnection();
	public List<ECheque> selectAllCheques();
	public ECheque selectCheque(long chequeId);
	public boolean insertCheque(ECheque eCheque);
	public int updateCheque(ECheque eCheque);
	public int deleteCheque(long chequeId);
	
}
