package com.bankapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.entity.ChequeDeposit;

@Repository
public interface ChequeDepositDAO extends JpaRepository<ChequeDeposit, Long> {

}
