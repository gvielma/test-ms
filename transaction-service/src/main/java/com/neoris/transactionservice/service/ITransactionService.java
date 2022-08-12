package com.neoris.transactionservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.neoris.transactionservice.dto.request.TransactionRequest;
import com.neoris.transactionservice.dto.response.TransactionResponse;

/**
 * The Interface ITransactionService.
 */
public interface ITransactionService {


	/**
	 * Gets the transaction.
	 *
	 * @param accountNumber the account number
	 * @return the transaction
	 */
	List<TransactionResponse> getTransaction(String accountNumber);
	
	
	/**
	 * Gets the transaction reports.
	 *
	 * @param accountNumber the account number
	 * @param fechaDesde the fecha desde
	 * @param fechaHasta the fecha hasta
	 * @return the transaction reports
	 */
	List<TransactionResponse> getTransactionReports(Long clienteID, LocalDateTime fechaDesde, LocalDateTime fechaHasta);

	/**
	 * Save transaction.
	 *
	 * @param transactionRequest the transaction request
	 * @return the transaction response
	 */
	TransactionResponse saveTransaction(TransactionRequest transactionRequest);

}
