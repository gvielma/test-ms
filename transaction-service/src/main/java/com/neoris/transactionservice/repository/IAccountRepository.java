package com.neoris.transactionservice.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.neoris.transactionservice.dto.response.AccountResponse;


@FeignClient(name= "account-service", path = "/api/cuentas")
public interface IAccountRepository {
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountResponse> getAccountByAccountNumber(
			@PathVariable(required = true) String accountNumber);
	
	@GetMapping("/cliente/{clienteID}")
	public ResponseEntity<List<AccountResponse>> getAccountsByClienteId(
			@PathVariable(required = true) Long clienteID);

}
