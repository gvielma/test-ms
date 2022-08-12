package com.neoris.transactionservice.errorhandler.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountNotExistsException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 8882043875788829903L;

	private final String codeError;

	public AccountNotExistsException(String msg, String codeError) {
		super(msg);
		this.codeError = codeError;
	}

}
