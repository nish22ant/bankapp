package com.bankapp.model.dao;

import java.util.List;

import com.bankapp.model.entity.ChequeDeposit;

public interface ChequeDepositDao {
	public List<ChequeDeposit> selectAllChequeDeposit();

	public boolean insertChequeDeposit(ChequeDeposit chequeDeposit);

	public int updateChequeDeposit(ChequeDeposit chequeDeposit);

	public int deleteChequeDeposit(long id);

	public ChequeDeposit selectChequeDeposit(long id);

}
