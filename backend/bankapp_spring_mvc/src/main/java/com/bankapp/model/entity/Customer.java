package com.bankapp.model.entity;

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
public class Customer {
	private long customerId;
	@NonNull
	private String customerName;
	@NonNull
	private String customerAddress;
	@NonNull
	private String customerMobile;
	@NonNull
	private String customerEmail;
	
}
