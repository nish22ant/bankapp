package com.bankapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class ChequeDeposit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NonNull
	@Column(name = "chequeNumber")
	private String chequeNumber;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "accountNumber")
	private Account account;

	@NonNull
	@Column(name = "chequeAmount")
	private double chequeAmount;

	@NonNull
	@Column(name = "chequeImage")
	private byte[] chequeImage;
}
