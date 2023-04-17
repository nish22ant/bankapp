package com.bankapp.model.dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bankapp.model.entity.Account;

@Repository
public class AccountDaoSpringJDBC implements AccountDao {

	@Override
	public Connection getDaoConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account selectAccount(long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int updateAccount(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccount(long accountNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Account> selectAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
