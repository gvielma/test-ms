package com.neoris.customerservice.errorhandler.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	/** The error messaje. */
	private String errorMessage;
	
	/** The error code. */
	private String errorCode;

}
