package com.bankapp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.entity.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

}
