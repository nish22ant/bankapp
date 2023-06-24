package com.bankapp.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bankapp.model.entity.Account;

/**
 * 
 * @author Nishant
 *
 */
@Repository(value = "accountDaoSpringJDBC")
@PropertySource("classpath:accountDaoQueries.properties")
@SuppressWarnings("deprecation")
@Primary
public class AccountDaoSpringJDBC implements AccountDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RowMapper<Account> accountRowMapper;
	
	@Autowired
	private Environment env;

	@Autowired
	public AccountDaoSpringJDBC(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Account selectAccount(long accountNumber) {
		return jdbcTemplate.queryForObject(env.getProperty("account.select"), new Object[] {accountNumber}, accountRowMapper);
	}

	@Override
	public boolean insertAccount(Account account) {
		int update = jdbcTemplate.update(env.getProperty("account.insert"), new Object[] {account.getCustomerId(), account.getAccountPassword(), account.getAccountBalance()});
		return update == 0 ? false : true;
	}

	@Override
	public int updateAccount(Account account) {
		return jdbcTemplate.update(env.getProperty("account.update"), new Object[] {account.getCustomerId(), account.getAccountPassword(), account.getAccountBalance(), account.getAccountNumber()});
	}

	@Override
	public int deleteAccount(long accountNumber) {
		return jdbcTemplate.update(env.getProperty("account.delete"), new Object[] {accountNumber});
	}

	@Override
	public List<Account> selectAllAccount() {
		return jdbcTemplate.query(env.getProperty("account.select.all"), accountRowMapper);
	}

}
