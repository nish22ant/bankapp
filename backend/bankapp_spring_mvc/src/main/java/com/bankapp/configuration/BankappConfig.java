package com.bankapp.configuration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Configuration file for bean creation
 * 
 * @author nknis
 *
 */

@Configuration
@ComponentScan("com.bankapp")
@PropertySource("classpath:accountMySQL.properties")
@EnableWebMvc
@EnableCaching
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
	private DataSource dataSource;

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
	 * @throws Exception
	 */
	@Bean(destroyMethod = "close")
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
	 * To clear datasource
	 */
	@PreDestroy
	public void cleanup() {
		if (dataSource instanceof ComboPooledDataSource) {
			((ComboPooledDataSource) dataSource).close();
		}
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
