package com.bankapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.entity.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
	public Account findByAccountNumber(long accountNumber);
}
