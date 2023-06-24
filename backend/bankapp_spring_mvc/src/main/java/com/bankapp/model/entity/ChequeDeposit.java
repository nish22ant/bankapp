package com.bankapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/***
 * For MySQL
 * 
 * @author nknis
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class ChequeDeposit {
	private long id;
	@NonNull
	private String chequeNumber;
	@NonNull
	private long accountNumber;
	@NonNull
	private String password;
	@NonNull
	private double chequeAmount;
	@NonNull
	private byte[] chequeImage;
}
