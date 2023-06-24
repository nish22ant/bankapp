package com.bankapp.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Configuration file for bean creation
 * @author nknis
 *
 */

@Configuration
@ComponentScan("com.bankapp")
@PropertySource("classpath:accountMySQL.properties")
@EnableWebMvc
public class BankappConfig {
	@Value("${driver}")
	private String driver;
	@Value("${url}")
	private String url;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;

	
	/**
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean(name = { "jdbcTemplate" })
	@Autowired
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		return dataSource;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Primary
	public DataSource c3P0DataSource() throws Exception {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(driver);
		comboPooledDataSource.setJdbcUrl(url);
		comboPooledDataSource.setUser(user);
		comboPooledDataSource.setPassword(password);
		comboPooledDataSource.setInitialPoolSize(20);
		comboPooledDataSource.setMaxPoolSize(100);
		comboPooledDataSource.setAcquireIncrement(5);
		comboPooledDataSource.setMaxIdleTime(3000);

		return comboPooledDataSource;
	}

	/**
	 * This bean is an instance of the CommonsMultipartResolver class, which is used
	 * for handling multipart/form-data requests in a Spring MVC application.
	 * 
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}

}
