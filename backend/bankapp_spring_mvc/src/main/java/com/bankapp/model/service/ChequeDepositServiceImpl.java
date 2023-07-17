package com.bankapp.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.exception.ChequeNotFoundException;
import com.bankapp.model.dao.ChequeDepositDao;
import com.bankapp.model.entity.ChequeDeposit;

/**
 * Service Implementation for ChequeDeposit
 * @author nknis
 *
 */
@Service
public class ChequeDepositServiceImpl implements ChequeDepositService {
	
	private Logger logger = LoggerFactory.getLogger(ChequeDepositServiceImpl.class);
	
	@Autowired
	private ChequeDepositDao chequeDepositDao;

	private static final String CHEQUE_EXCEPTION_MESSAGE = "Cheque Not Found!";
	
	public ChequeDepositServiceImpl(ChequeDepositDao chequeDepositDao) {
		this.chequeDepositDao = chequeDepositDao;
	}
	
	@Override
	public ChequeDeposit selectChequeDeposit(long id) {
		ChequeDeposit chequeDeposit = chequeDepositDao.selectChequeDeposit(id);
		return chequeDeposit;
	}

	@Override
	public List<ChequeDeposit> selectAllChequeDeposit() {
		List<ChequeDeposit> chequeDeposits = chequeDepositDao.selectAllChequeDeposit();
		return chequeDeposits;
	}

	@Override
	public boolean insertChequeDeposit(ChequeDeposit chequeDeposit) {
		logger.info("Inside insert");
		boolean isChequeDeposit = chequeDepositDao.insertChequeDeposit(chequeDeposit);
		return isChequeDeposit;
	}

	@Override
	public int updateChequeDeposit(ChequeDeposit chequeDeposit) {
		ChequeDeposit chequeDep = chequeDepositDao.selectChequeDeposit(chequeDeposit.getAccountNumber());
		if(chequeDep != null) {
			return chequeDepositDao.updateChequeDeposit(chequeDeposit);
		} else {
			throw new ChequeNotFoundException(CHEQUE_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public int deleteChequeDeposit(long id) {
		ChequeDeposit chequeDep = chequeDepositDao.selectChequeDeposit(id);
		if(chequeDep != null) {
			return chequeDepositDao.deleteChequeDeposit(id);
		} else {
			throw new ChequeNotFoundException(CHEQUE_EXCEPTION_MESSAGE);
		}
	}

}
