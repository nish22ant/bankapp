package com.bankapp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.exception.BankAccountNotFoundException;
import com.bankapp.exception.IncorrectPasswordException;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.entity.Account;

@Transactional
@Service(value = "accountService")
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountDao accountDao;
	private static final String BANK_ACCOUNT_EXCEPTION_MESSAGE = "Bank Account Not Found!";
	private static final String INCORRECT_PASSWORD_EXCEPTION_MESSAGE = "Password is Incorrect!";

	public AccountServiceImp(AccountDao accountDao) {
		this.accountDao = accountDao;
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
	public int updateAccount(Account account) {
		Account acc = accountDao.selectAccount(account.getAccountNumber());
		if (acc != null) {
			return accountDao.updateAccount(account);
		} else {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}

	}

	@Override
	public int deleteAccount(long accountNumber) {
		Account account = accountDao.selectAccount(accountNumber);
		if (account != null) {
			return accountDao.deleteAccount(accountNumber);
		} else {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}

	}

	@Override
	public boolean addAccount(Account account) {
		return accountDao.insertAccount(account);
	}

	@Override
	public double deposit(long accountNumber, double amount, String password) {
		double balance = -1;
		Account account = accountDao.selectAccount(accountNumber);
		if (account == null) {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
		balance = account.getAccountBalance() + amount;
		account.setAccountBalance(balance);
		accountDao.updateAccount(account);
		return balance;
	}

	@Override
	public double withdraw(long accountNumber, double amount, String password) {
		double balance = -1;
		Account account = accountDao.selectAccount(accountNumber);
		if (account == null) {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
		balance = account.getAccountBalance() - amount;
		account.setAccountBalance(balance);
		accountDao.updateAccount(account);
		return balance;
	}

	@Override
	public int transfer(long fromAccountNumber, long toAccountNumber, double amount, String password) {
		int status = 0;
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
		status = 1;
		return status;

	}

	@Override
	public int changePassword(long accountNumber, String currentPassword, String newPassword) {
		int status = 0;
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
		status = 1;

		return status;
	}

}