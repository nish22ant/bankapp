package com.bankapp.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bankapp.model.entity.ChequeDeposit;

@Component
public class ChequeDepositRowMapper implements RowMapper<ChequeDeposit> {

	@Override
	public ChequeDeposit mapRow(ResultSet rs, int rowNum) throws SQLException {
		long id = rs.getLong("id");
		String chequeNumber = rs.getString("chequeNumber");
		long accountNumber = rs.getLong("accountNumber");
		String password = rs.getString("accountPassword");
		byte[] chequeImage = rs.getBytes("chequeImage");
		double chequeAmount = rs.getDouble("chequeAmount");

		return new ChequeDeposit(id, chequeNumber, accountNumber, password, chequeAmount, chequeImage);
	}

}
