package com.neoris.accountservice.util;

import java.util.stream.Stream;

public enum AccountStatus {

	TRUE("True"), 
	FALSE("False");

	String status;

	AccountStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public static AccountStatus fromStatus(String status) {
	     if (status == null) {
	            return null;
	        }
	     return Stream.of(AccountStatus.values())
	                .filter(c -> c.getStatus().equals(status))
	                .findFirst()
	                .orElseThrow(IllegalArgumentException::new);
	}
}