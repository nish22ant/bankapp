package com.bankapp.model.dao;

import java.sql.Connection;
import java.util.List;

import com.bankapp.model.dto.Account;

public interface AccountDao {
	public Connection getDaoConnection();
	public Account selectAccount(long accountNumber);
	public boolean insertAccount(Account account);
	public int updateAccount(Account account);
	public int deleteAccount(long accountNumber);
	public List<Account> selectAllAccount();
}
