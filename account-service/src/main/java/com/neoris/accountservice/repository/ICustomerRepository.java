package com.neoris.accountservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.neoris.accountservice.dto.response.CustomerResponse;

@FeignClient(name = "customer-service", path = "/api/clientes")
public interface ICustomerRepository {

	@GetMapping("/codigo/{customerID}")
	public ResponseEntity<CustomerResponse> getCustomerByCustomerID(@PathVariable(required = true) Long customerID);

}
