package com.neoris.transactionservice.errorhandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neoris.transactionservice.errorhandler.exception.AccountNotExistsException;
import com.neoris.transactionservice.errorhandler.exception.ConflictException;
import com.neoris.transactionservice.errorhandler.exception.ExceptionResponse;
import com.neoris.transactionservice.errorhandler.exception.SendEventException;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalControllerAdvice {

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<Object> businessLogicExceptions(Exception ex) {

		log.error("Error: {}", ex.getMessage());

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ AccountNotExistsException.class })
	public ResponseEntity<ExceptionResponse> accountNotExistsException(AccountNotExistsException ex) {

		log.error("Error: {}", ex.getMessage());

		ExceptionResponse errorResponse = ExceptionResponse.builder().errorMessage(ex.getMessage())
				.errorCode(ex.getCodeError()).build();

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ConflictException.class, SendEventException.class})
	public ResponseEntity<ExceptionResponse> conflictException(ConflictException ex) {

		log.error("Error: {}", ex.getMessage());

		ExceptionResponse errorResponse = ExceptionResponse.builder().errorMessage(ex.getMessage())
				.errorCode(ex.getCodeError()).build();

		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ExceptionResponse> handleFeignStatusException(FeignException e) {

		ExceptionResponse errorResponse = ExceptionResponse.builder()
				.errorMessage("Error: Servicio Account-Service no disponible")
				.errorCode("TRANSACTION010")
				.build();

		if (e.status() == 404) {

			errorResponse.setErrorMessage("La cuenta no existe");
		}

		return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.status()));
	}

}
