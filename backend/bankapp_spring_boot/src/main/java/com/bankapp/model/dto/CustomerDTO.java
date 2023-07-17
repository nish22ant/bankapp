package com.bankapp.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class CustomerDTO {
	private long customerId;
	private String customerName;
	private String customerAddress;
	private String customerMobile;
	private String customerEmail;
}
