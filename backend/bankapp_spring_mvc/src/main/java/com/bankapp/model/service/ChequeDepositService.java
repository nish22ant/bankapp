package com.bankapp.model.service;

import java.util.List;

import com.bankapp.model.entity.ChequeDeposit;

public interface ChequeDepositService {
	public ChequeDeposit selectChequeDeposit(long id);
	public List<ChequeDeposit> selectAllChequeDeposit();
	public boolean insertChequeDeposit(ChequeDeposit chequeDeposit);
	public int updateChequeDeposit(ChequeDeposit chequeDeposit);
	public int deleteChequeDeposit(long id);
}
