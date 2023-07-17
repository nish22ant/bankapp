package com.bankapp.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountNumber")
	private long accountNumber;

	@NonNull
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "customer_account", joinColumns = @JoinColumn(name = "accountNumber"), inverseJoinColumns = @JoinColumn(name = "customerId"))
	private List<Customer> customers = new ArrayList<>();
	
	@NonNull
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<ChequeDeposit> chequeDeposits;
	
	@NonNull
	@Column(name = "accountPassword")
	private String accountPassword;
	
	@NonNull
	@Column(name = "accountBalance")
	private double accountBalance;
	
	@Override
	public String toString() {
	    return "Account [accountNumber=" + accountNumber + ", balance=" + accountBalance + "]";
	}

}
