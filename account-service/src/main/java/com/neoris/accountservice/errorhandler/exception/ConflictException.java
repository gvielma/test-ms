package com.neoris.accountservice.errorhandler.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 8882043875788829903L;

	private final String codeError;

	public ConflictException(String msg, String codeError) {
		super(msg);
		this.codeError = codeError;
	}

}
