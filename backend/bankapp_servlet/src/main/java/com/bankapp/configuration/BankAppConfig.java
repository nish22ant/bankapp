package com.bankapp.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bankapp.model.connectionfactory.ConnectionFactoryMySQL;
import com.bankapp.model.dao.AccountDao;
import com.bankapp.model.dao.AccountDaoJDBC;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.AccountServiceImp;

@Configuration
@ComponentScan(basePackages = {"com.bankapp"})
public class BankAppConfig {
	@Bean
	public Connection getConnection() throws SQLException {
		return ConnectionFactoryMySQL.getConnection();
	}
	
	@Bean
	@Autowired
	public AccountDao getAccountDao(Connection connection) {
		return new AccountDaoJDBC(connection);
	}
	
	@Bean(name = "acc")
	@Autowired
	public AccountService getAccountService(AccountDao accountDao) {
		return new AccountServiceImp(accountDao);
	}
}
