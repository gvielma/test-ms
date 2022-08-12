package com.neoris.transactionservice.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.transactionservice.dto.request.TransactionRequest;
import com.neoris.transactionservice.dto.response.TransactionResponse;
import com.neoris.transactionservice.service.ITransactionService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {

	@Autowired
	private ITransactionService transactionService;

	@GetMapping("/{accountNumber}")
	public ResponseEntity<List<TransactionResponse>> getTransactionByAccountNumber(
			@PathVariable(required = true) String accountNumber) {

		return new ResponseEntity<>(transactionService.getTransaction(accountNumber), HttpStatus.OK);
	}

	@GetMapping("/{clienteID}/reportes")
	public ResponseEntity<List<TransactionResponse>> getTransactionReports(
			@PathVariable(required = true) Long clienteID,
			@RequestParam(required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate desde,
			@RequestParam(required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate hasta) {

		return new ResponseEntity<>(
				transactionService.getTransactionReports(clienteID, desde.atStartOfDay(), hasta.atTime(11, 59, 59)),
				HttpStatus.OK);
	}

	/**
	 * Save account.
	 *
	 * @param accountRequest the account request
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<TransactionResponse> saveTransaction(
			@RequestBody @NotNull @Valid TransactionRequest transactionRequest) {

		return new ResponseEntity<>(transactionService.saveTransaction(transactionRequest), HttpStatus.OK);
	}

}
