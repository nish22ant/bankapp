package com.bankapp.model.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bankapp.exception.BankAccountNotFoundException;
import com.bankapp.exception.IncorrectPasswordException;
import com.bankapp.model.dao.AccountDAO;
import com.bankapp.model.dto.AccountRequestDTO;
import com.bankapp.model.dto.AccountResponseDTO;
import com.bankapp.model.entity.Account;

/**
 * This class provides the implementation of the AccountService interface.
 * 
 * @author Nishant
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	private static final String BANK_ACCOUNT_EXCEPTION_MESSAGE = "Bank Account Not Found!";
	private static final String INCORRECT_PASSWORD_EXCEPTION_MESSAGE = "Password is Incorrect!";

	/**
	 * Retrieves a list of all accounts.
	 *
	 * @return the list of accounts
	 */
	@Override
	@Transactional
	public List<Account> getAllAccounts() {
		return accountDAO.findAll();
	}

	/**
	 * Retrieves an account by account number.
	 *
	 * @param accountNumber the account number
	 * @return the account
	 * @throws BankAccountNotFoundException if the account is not found
	 */
	@Cacheable(value = "accounts", key = "#accountNumber")
	@Transactional
	@Override
	public AccountResponseDTO getAccount(long accountNumber) {
	    Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
	    if (optionalAccount.isPresent()) {
	        Account account = optionalAccount.get();
	        AccountResponseDTO accountDTO = modelMapper.map(account, AccountResponseDTO.class);
	        return accountDTO;
	    } else {
	        throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
	    }
	}


	/**
	 * Updates an existing account.
	 *
	 * @param account the account to be updated
	 * @return the account number of the updated account
	 * @throws BankAccountNotFoundException if the account is not found
	 */
	@Override
	public int updateAccount(Account account) {
		Account acc = accountDAO.findByAccountNumber(account.getAccountNumber());
		if (acc == null) {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
		return (int) accountDAO.save(acc).getAccountNumber();

	}

	/**
	 * Deletes an account by account number.
	 *
	 * @param accountNumber the account number
	 * @return 1 if the account is successfully deleted
	 * @throws BankAccountNotFoundException if the account is not found
	 */
	@Override
	public int deleteAccount(long accountNumber) {
		Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
		if (optionalAccount.isPresent()) {
			accountDAO.deleteById(accountNumber);
			return 1;
		} else {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
	}

	/**
	 * Adds a new account.
	 *
	 * @param account the account to be added
	 * @return true if the account is successfully added
	 */
	@Override
	public AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO) {
		Account account = modelMapper.map(accountRequestDTO, Account.class);
		account.getCustomers().add(accountRequestDTO.getCustomer());
		Account savedAccount = accountDAO.save(account);
		return modelMapper.map(savedAccount, AccountResponseDTO.class);
	}

	/**
	 * Deposits an amount to the specified account.
	 *
	 * @param accountNumber the account number
	 * @param amount        the amount to be deposited
	 * @param password      the account password for verification
	 * @return the new balance after deposit
	 * @throws BankAccountNotFoundException if the account is not found
	 * @throws IncorrectPasswordException   if the password is incorrect
	 */
	@Override
	public double deposit(long accountNumber, double amount, String password) {
		Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
		if (optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			double balance = account.getAccountBalance();
			String accountPassword = account.getAccountPassword();
			if (accountPassword.equals(password)) {
				double newBalance = balance + amount;
				account.setAccountBalance(newBalance);
				accountDAO.save(account);
				return newBalance;
			} else {
				throw new IncorrectPasswordException(INCORRECT_PASSWORD_EXCEPTION_MESSAGE);
			}
		} else {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
	}

	/**
	 * Withdraws an amount from the specified account.
	 *
	 * @param accountNumber the account number
	 * @param amount        the amount to be withdrawn
	 * @param password      the account password for verification
	 * @return the new balance after withdrawal
	 * @throws BankAccountNotFoundException if the account is not found
	 * @throws IncorrectPasswordException   if the password is incorrect
	 */
	@Override
	public double withdraw(long accountNumber, double amount, String password) {
		Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
		if (optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			double balance = account.getAccountBalance();
			String accountPassword = account.getAccountPassword();
			if (accountPassword.equals(password)) {
				double newBalance = balance - amount;
				account.setAccountBalance(newBalance);
				accountDAO.save(account);
				return newBalance;
			} else {
				throw new IncorrectPasswordException(INCORRECT_PASSWORD_EXCEPTION_MESSAGE);
			}
		} else {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
	}

	/**
	 * Transfers an amount from one account to another.
	 *
	 * @param fromAccountNumber   the account number from which the amount will be
	 *                            withdrawn
	 * @param toAccountNumber     the account number to which the amount will be
	 *                            deposited
	 * @param amount              the amount to be transferred
	 * @param fromAccountPassword the password of the fromAccount for verification
	 * @return 1 if the transfer is successful
	 * @throws BankAccountNotFoundException if any of the accounts are not found
	 * @throws IncorrectPasswordException   if the fromAccount password is incorrect
	 */
	@Override
	public int transfer(long fromAccountNumber, long toAccountNumber, double amount, String fromAccountPassword) {
		double withdrawnAmount = withdraw(fromAccountNumber, amount, fromAccountPassword);

		Optional<Account> optionalToAccount = accountDAO.findById(toAccountNumber);
		if (optionalToAccount.isPresent()) {
			Account toAccount = optionalToAccount.get();
			String toAccountPassword = toAccount.getAccountPassword();

			if (!toAccountPassword.isEmpty() && !toAccountPassword.equals(fromAccountPassword)) {
				throw new IncorrectPasswordException(INCORRECT_PASSWORD_EXCEPTION_MESSAGE);
			}

			double depositedAmount = deposit(toAccountNumber, amount, fromAccountPassword);

			if (withdrawnAmount > 0 && depositedAmount > 0) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Changes the password of an account.
	 *
	 * @param accountNumber   the account number
	 * @param currentPassword the current password
	 * @param newPassword     the new password
	 * @return 1 if the password is successfully changed
	 * @throws BankAccountNotFoundException if the account is not found
	 * @throws IncorrectPasswordException   if the current password is incorrect
	 */
	@Override
	public int changePassword(long accountNumber, String currentPassword, String newPassword) {
		Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
		if (optionalAccount.isPresent()) {
			Account account = optionalAccount.get();
			if (account.getAccountPassword().equals(currentPassword)) {
				account.setAccountPassword(newPassword);
				accountDAO.save(account);
				return 1;
			} else {
				throw new IncorrectPasswordException(INCORRECT_PASSWORD_EXCEPTION_MESSAGE);
			}
		} else {
			throw new BankAccountNotFoundException(BANK_ACCOUNT_EXCEPTION_MESSAGE);
		}
	}
}
