package com.bankapp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bankapp.model.entity.Account;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountDaoJDBC implements AccountDao {
	private Connection connection = null;
	private PreparedStatement preparedStmt = null;

	private final String SELECT_ACCOUNT_BY_NUMBER = "SELECT * FROM accounts WHERE accountNumber = ?";
	private final String SELECT_ALL_ACCOUNTS = "SELECT * FROM accounts";
	private final String ADD_ACCOUNT = "INSERT INTO accounts(customerId, accountPassword, accountBalance) VALUES(?, ?, ?)";
	private final String UPDATE_ACCOUNT = "UPDATE accounts SET customerId = ?, accountPassword = ?, accountBalance = ? WHERE accountNumber = ?";
	private final String DELETE_ACCOUNT = "DELETE FROM accounts WHERE accountNumber = ?";

	public AccountDaoJDBC(Connection connection) {
		this.connection = connection;
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getDaoConnection() {
		return connection;
	}

	@Override
	public Account selectAccount(long accountNumber) {
		Account account = null;
		try {
			preparedStmt = this.connection.prepareStatement(SELECT_ACCOUNT_BY_NUMBER);
			preparedStmt.setLong(1, accountNumber);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				long customerId = rs.getLong(2);
				String accountPassword = rs.getString(3);
				double accountBalance = rs.getDouble(4);
				account = new Account(accountNumber, customerId, accountPassword, accountBalance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> selectAllAccount() {
		List<Account> accounts = new ArrayList<>();
		try {
			preparedStmt = this.connection.prepareStatement(SELECT_ALL_ACCOUNTS);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				long accountNumber = rs.getLong(1);
				long customerId = rs.getLong(2);
				String accountPassword = rs.getString(3);
				double accountBalance = rs.getDouble(4);
				accounts.add(new Account(accountNumber, customerId, accountPassword, accountBalance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;

	}

	@Override
	public int updateAccount(Account account) {
		try {
			preparedStmt = this.connection.prepareStatement(UPDATE_ACCOUNT);
			preparedStmt.setLong(1, account.getCustomerId());
			preparedStmt.setString(2, account.getAccountPassword());
			preparedStmt.setDouble(3, account.getAccountBalance());
			preparedStmt.setLong(4, account.getAccountNumber());
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public boolean insertAccount(Account account) {
		try {
			preparedStmt = this.connection.prepareStatement(ADD_ACCOUNT);
			preparedStmt.setLong(1, account.getCustomerId());
			preparedStmt.setString(2, account.getAccountPassword());
			preparedStmt.setDouble(3, account.getAccountBalance());
			return preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int deleteAccount(long accountNumber) {
		try {
			preparedStmt = this.connection.prepareStatement(DELETE_ACCOUNT);
			preparedStmt.setLong(1, accountNumber);
			return preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}
}
