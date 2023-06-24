package com.bankapp.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bankapp.model.entity.Account;

@Component
public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		long accountNumber = rs.getLong("accountNumber");
		long customerId = rs.getLong("customerId");
		String accountPassword = rs.getString("accountPassword");
		double accountBalance = rs.getDouble("accountBalance");
		Account account = new Account(accountNumber, customerId, accountPassword, accountBalance);
		return account;
	}

}
