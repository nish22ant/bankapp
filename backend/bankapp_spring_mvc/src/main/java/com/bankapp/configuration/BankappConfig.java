package com.bankapp.configuration;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.bankapp")
@PropertySource("classpath:accountMySQL.properties")
public class BankappConfig {
	@Value("${driver}")
	private String driver;
	@Value("${url}")
	private String url;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;
	
	@Autowired
	@Bean
	public JdbcTemplate getJdbcTemplate(DriverManagerDataSource driverManagerDataSource) {
		return new JdbcTemplate(driverManagerDataSource);
	}
	
	@Bean
	public DataSource getMySQLDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		return dataSource;
	}
	
	
}
