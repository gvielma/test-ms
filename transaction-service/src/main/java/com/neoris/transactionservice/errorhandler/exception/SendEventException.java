package com.neoris.transactionservice.errorhandler.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEventException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String codeError;

	public SendEventException(String msg, String codeError) {
		super(msg);
		this.codeError = codeError;
	}
}
