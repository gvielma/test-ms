package com.neoris.accountservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.accountservice.dto.request.AccountRequest;
import com.neoris.accountservice.dto.response.AccountResponse;
import com.neoris.accountservice.service.IAccountService;
import com.sun.istack.NotNull;

/**
 * The Class AccountController.
 */
@RestController
@RequestMapping("/cuentas")
public class AccountController {

	/** The account service. */
	@Autowired
	private IAccountService accountService;

	/**
	 * Gets the account by account number.
	 *
	 * @param accountNumber the account number
	 * @return the account by account number
	 */
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountResponse> getAccountByAccountNumber(
			@PathVariable(required = true) String accountNumber) {

		return new ResponseEntity<>(accountService.getAccount(accountNumber), HttpStatus.OK);
	}
	
	/**
	 * Gets the accounts by cliente id.
	 *
	 * @param clienteID the cliente ID
	 * @return the accounts by cliente id
	 */
	@GetMapping("/cliente/{clienteID}")
	public ResponseEntity<List<AccountResponse>> getAccountsByClienteId(
			@PathVariable(required = true) Long clienteID) {

		return new ResponseEntity<>(accountService.getAccountsByClienteId(clienteID), HttpStatus.OK);
	}

	/**
	 * Save account.
	 *
	 * @param accountRequest the account request
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<AccountResponse> saveAccount(@RequestBody @NotNull @Valid AccountRequest accountRequest) {

		return new ResponseEntity<>(accountService.saveAccount(accountRequest), HttpStatus.OK);
	}


	/**
	 * Update account.
	 *
	 * @param id the id
	 * @param accountRequest the account request
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<AccountResponse> updateAccount(@PathVariable(required = true) Long id,
			@RequestBody @NotNull @Valid AccountRequest accountRequest) {

		return new ResponseEntity<>(accountService.updateAccount(accountRequest, id), HttpStatus.OK);
	}

	/**
	 * Disable account.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> disableAccount(@PathVariable(required = true) Long id) {

		accountService.deleteAccount(id);

		return new ResponseEntity<>("Cuenta desactivada exitosamente", HttpStatus.OK);
	}
}
