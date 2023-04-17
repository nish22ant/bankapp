package com.bankapp.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bankapp.exception.BankAccountNotFoundException;
import com.bankapp.exception.IncorrectPasswordException;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.entity.Account;

public class AccountServiceImp implements AccountService {
	private AccountDao accountDao;
	private Connection connection;
	private static final String BANK_ACCOUNT_EXCEPTION_MESSAGE = "Bank Account Not Found!";
	private static final String INCORRECT_PASSWORD_EXCEPTION_MESSAGE = "Password is Incorrect!";

	public AccountServiceImp(AccountDao accountDao) {
		this.accountDao = accountDao;
		this.connection = accountDao.getDaoConnection();
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountDao.selectAllAccount();
	}

	@Override
	public Account getAccount(long accountNumber) {
		Account account = accountDao.selectAccount(accountNumber);
		if (account == null) {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		} else {
			return account;
		}
	}

	@Override
	public int updateAccount(Account account) throws SQLException {
		int status = 0;
		try {
			connection.setAutoCommit(false);
			Account acc = accountDao.selectAccount(account.getAccountNumber());
			if (acc != null) {
				status = accountDao.updateAccount(account);
				connection.commit();
			} else {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
		} catch (SQLException e) {
			connection.rollback();
		}
		return status;

	}

	@Override
	public int deleteAccount(long accountNumber) throws SQLException {
		int status = 0;
		try {
			connection.setAutoCommit(false);
			Account account = accountDao.selectAccount(accountNumber);
			if (account != null) {
				status = accountDao.deleteAccount(accountNumber);
				connection.commit();
			} else {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
		} catch (SQLException e) {
			connection.rollback();
		}
		return status;

	}

	@Override
	public boolean addAccount(Account account) throws SQLException {
		boolean status = false;
		try {
			connection.setAutoCommit(false);
			status = accountDao.insertAccount(account);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		}
		return status;
	}

	@Override
	public double deposit(long accountNumber, double amount, String password) throws SQLException {
		double balance = -1;
		try {
			connection.setAutoCommit(false);
			Account account = accountDao.selectAccount(accountNumber);
			if (account == null) {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
			balance = account.getAccountBalance() + amount;
			account.setAccountBalance(balance);
			accountDao.updateAccount(account);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} 
		return balance;
	}

	@Override
	public double withdraw(long accountNumber, double amount, String password) throws SQLException {
		double balance = -1;
		try {
			connection.setAutoCommit(false);
			Account account = accountDao.selectAccount(accountNumber);
			if (account == null) {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
			balance = account.getAccountBalance() - amount;
			account.setAccountBalance(balance);
			accountDao.updateAccount(account);
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
		} 
		return balance;
	}

	@Override
	public int transfer(long fromAccountNumber, long toAccountNumber, double amount, String password)
			throws SQLException {
		int status = 0;
		try {
			connection.setAutoCommit(false);
			Account fromAccount = accountDao.selectAccount(fromAccountNumber);
			Account toAccount = accountDao.selectAccount(toAccountNumber);
			if (fromAccount == null) {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
			if (toAccount == null) {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
			withdraw(fromAccountNumber, amount, password);
			deposit(toAccountNumber, amount, password);
			connection.commit();
			status = 1;
		} catch (SQLException e) {
			connection.rollback();
		}
		return status;

	}

	@Override
	public int changePassword(long accountNumber, String currentPassword, String newPassword) throws SQLException {
		int status = 0;

		try {
			connection.setAutoCommit(false);
			Account account = accountDao.selectAccount(accountNumber);
			if (account == null) {
				throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
			}
			String accountPassword = accountDao.selectAccount(accountNumber).getAccountPassword();
			if (accountPassword.equals(currentPassword)) {
				accountDao.selectAccount(accountNumber).setAccountPassword(newPassword);

			} else {
				throw new IncorrectPasswordException(INCORRECT_PASSWORD_EXCEPTION_MESSAGE);
			}
			connection.commit();
			status = 1;
		} catch (SQLException e) {
			connection.rollback();
		}

		return status;
	}

}