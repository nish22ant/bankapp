// Customer.java
package com.bankapp.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
	private long customerId;
	
	@NonNull
	@Column(name = "customerName")
	private String customerName;
	
	@NonNull
	@Column(name = "customerAddress")
	private String customerAddress;
	
	@NonNull
	@Column(name = "customerMobile")
	private String customerMobile;
	
	@NonNull
	@Column(name = "customerEmail")
	private String customerEmail;
	
	@ManyToMany(mappedBy = "customers")
	private List<Account> accounts;


}
