package com.bankapp.model.service;

import java.sql.SQLException;
import java.util.List;

import com.bankapp.model.entity.Account;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	public Account getAccount(long accountNumber);
	public int updateAccount(Account account) throws SQLException;
	public int deleteAccount(long accountNumber) throws SQLException;
	public boolean addAccount(Account account) throws SQLException;
	public double deposit(long accountNumber, double amount, String password) throws SQLException;
	public double withdraw(long accountNumber, double amount, String password) throws SQLException;
	public int transfer(long fromAccountNumber, long toAccountNumber, double amount, String password) throws SQLException;
	public int changePassword(long accountNumber, String currentPassword, String newPassword) throws SQLException;
}
