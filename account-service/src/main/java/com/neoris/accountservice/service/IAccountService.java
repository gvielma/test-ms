package com.neoris.accountservice.service;

import java.util.List;

import com.neoris.accountservice.dto.request.AccountRequest;
import com.neoris.accountservice.dto.request.TransactionRequest;
import com.neoris.accountservice.dto.response.AccountResponse;

/**
 * The Interface IAccountService.
 */
public interface IAccountService {

	/**
	 * Gets the account.
	 *
	 * @param accountNumber the account number
	 * @return the account
	 */
	AccountResponse getAccount(String accountNumber);

	/**
	 * Gets the accounts by cliente id.
	 *
	 * @param clientID the client ID
	 * @return the accounts by cliente id
	 */
	List<AccountResponse> getAccountsByClienteId(Long clientID);

	/**
	 * Save account.
	 *
	 * @param accountRequest the account request
	 * @return the account response
	 */
	AccountResponse saveAccount(AccountRequest accountRequest);

	/**
	 * Update account.
	 *
	 * @param accountRequest the account request
	 * @param id             the id
	 * @return the account response
	 */
	AccountResponse updateAccount(AccountRequest accountRequest, Long id);

	/**
	 * Delete account.
	 *
	 * @param id the id
	 */
	void deleteAccount(Long id);

	/**
	 * Apply transaction.
	 *
	 * @param transactionRequest the transaction request
	 */
	void applyTransaction(TransactionRequest transactionRequest);

}
