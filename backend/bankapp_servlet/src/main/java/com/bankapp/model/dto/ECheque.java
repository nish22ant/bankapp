package com.bankapp.model.dto;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Data
public class ECheque {
	@NonNull
	private long chequeId;
	@NonNull
	private byte[] chequeImage;
	@NonNull
	private Date chequeDepositDate;
}
