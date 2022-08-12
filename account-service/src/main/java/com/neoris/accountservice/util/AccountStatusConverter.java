package com.neoris.accountservice.util;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus, String> {

	@Override
	public String convertToDatabaseColumn(AccountStatus accountStatus) {
		if (accountStatus == null) {
			return null;
		}
		return accountStatus.getStatus();
	}

	@Override
	public AccountStatus convertToEntityAttribute(String status) {
	     if (status == null) {
	            return null;
	        }
	     return Stream.of(AccountStatus.values())
	                .filter(c -> c.getStatus().equals(status))
	                .findFirst()
	                .orElseThrow(IllegalArgumentException::new);
	}

}
