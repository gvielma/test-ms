package com.neoris.customerservice.errorhandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neoris.customerservice.errorhandler.exception.ConflictException;
import com.neoris.customerservice.errorhandler.exception.CustomerNotExistsException;
import com.neoris.customerservice.errorhandler.exception.ExceptionResponse;

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

	@ExceptionHandler({ CustomerNotExistsException.class })
	public ResponseEntity<ExceptionResponse> customerNotExistsException(CustomerNotExistsException ex) {

		log.error("Error: {}", ex.getMessage());

		ExceptionResponse errorResponse = ExceptionResponse.builder().errorMessage(ex.getMessage())
				.errorCode(ex.getCodeError()).build();

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ConflictException.class })
	public ResponseEntity<ExceptionResponse> conflictException(ConflictException ex) {

		log.error("Error: {}", ex.getMessage());

		ExceptionResponse errorResponse = ExceptionResponse.builder().errorMessage(ex.getMessage())
				.errorCode(ex.getCodeError()).build();

		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

}
