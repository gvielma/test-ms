package com.neoris.accountservice.util;

import java.util.stream.Stream;

public enum AccountType {

	AHORRO("Ahorro"), 
	CORRIENTE("Corriente");

	String type;

	AccountType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static AccountType fromType(String type) {
	     if (type == null) {
	            return null;
	        }
	     return Stream.of(AccountType.values())
	                .filter(c -> c.getType().equals(type))
	                .findFirst()
	                .orElseThrow(IllegalArgumentException::new);
	}

}