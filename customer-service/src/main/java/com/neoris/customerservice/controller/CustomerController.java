package com.neoris.customerservice.controller;

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

import com.neoris.customerservice.dto.request.CustomerRequest;
import com.neoris.customerservice.dto.response.CustomerResponse;
import com.neoris.customerservice.service.ICustomerService;
import com.sun.istack.NotNull;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(required = true) Long id) {

		return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
	}

	@GetMapping("/codigo/{customerID}")
	public ResponseEntity<CustomerResponse> getCustomerByCustomerID(@PathVariable(required = true) Long customerID) {

		return new ResponseEntity<>(customerService.getCustomerByCustomerID(customerID), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody @NotNull @Valid CustomerRequest customerRequest) {

		return new ResponseEntity<>(customerService.saveCustomer(customerRequest), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable(required = true) Long id,
			@RequestBody @NotNull @Valid CustomerRequest customerRequest) {

		return new ResponseEntity<>(customerService.updateCustomer(customerRequest, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> disableCustomer(@PathVariable(required = true) Long id) {

		customerService.deleteCustomer(id);

		return new ResponseEntity<>("Cliente desactivada exitosamente", HttpStatus.OK);
	}
}
