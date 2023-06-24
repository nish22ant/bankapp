package com.bankapp.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bankapp.model.entity.ChequeDeposit;

@Repository
@PropertySource("classpath:chequeDepositDaoQueries.properties")
public class ChequeDepositDaoImpl implements ChequeDepositDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ChequeDepositRowMapper chequeDepositRowMapper;

	@Autowired
	private Environment env;

	@Autowired
	public ChequeDepositDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ChequeDeposit selectChequeDeposit(long id) {
		return jdbcTemplate.queryForObject(env.getProperty("eCheque.select"), chequeDepositRowMapper, id);
	}

	@Override
	public List<ChequeDeposit> selectAllChequeDeposit() {
		return jdbcTemplate.query(env.getProperty("eCheque.select.all"), chequeDepositRowMapper);
	}

	@Override
	public boolean insertChequeDeposit(ChequeDeposit chequeDeposit) {
		int update = jdbcTemplate.update(env.getProperty("eCheque.insert"),
				new Object[] { chequeDeposit.getChequeNumber(), chequeDeposit.getAccountNumber(),
						chequeDeposit.getPassword(), chequeDeposit.getChequeImage(), chequeDeposit.getChequeAmount() });
		return update == 0 ? false : true;
	}

	@Override
	public int updateChequeDeposit(ChequeDeposit chequeDeposit) {
		return jdbcTemplate.update(env.getProperty("eCheque.update"),
				new Object[] { chequeDeposit.getChequeNumber(), chequeDeposit.getAccountNumber(),
						chequeDeposit.getChequeImage(), chequeDeposit.getChequeAmount(), chequeDeposit.getId() });
	}

	@Override
	public int deleteChequeDeposit(long id) {
		return jdbcTemplate.update(env.getProperty("eCheque.delete"), id);
	}
}
