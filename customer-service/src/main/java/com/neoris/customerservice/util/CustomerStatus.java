package com.neoris.customerservice.util;

import java.util.stream.Stream;

public enum CustomerStatus {

	TRUE("True"), 
	FALSE("False");

	String status;

	CustomerStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	public static CustomerStatus fromStatus(String status) {
	     if (status == null) {
	            return null;
	        }
	     return Stream.of(CustomerStatus.values())
	                .filter(c -> c.getStatus().equals(status))
	                .findFirst()
	                .orElseThrow(IllegalArgumentException::new);
	}
}