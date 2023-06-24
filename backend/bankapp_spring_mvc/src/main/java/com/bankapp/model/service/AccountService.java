package com.bankapp.model.service;

import java.util.List;

import com.bankapp.model.entity.Account;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	public Account getAccount(long accountNumber);
	public int updateAccount(Account account);
	public int deleteAccount(long accountNumber);
	public boolean addAccount(Account account);
	public double deposit(long accountNumber, double amount, String password);
	public double withdraw(long accountNumber, double amount, String password);
	public int transfer(long fromAccountNumber, long toAccountNumber, double amount, String password);
	public int changePassword(long accountNumber, String currentPassword, String newPassword);
}
