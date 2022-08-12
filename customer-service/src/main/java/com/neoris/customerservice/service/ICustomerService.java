package com.neoris.customerservice.service;

import com.neoris.customerservice.dto.request.CustomerRequest;
import com.neoris.customerservice.dto.response.CustomerResponse;

/**
 * The Interface ICustomerService.
 */
public interface ICustomerService {

	/**
	 * Gets the customer by id.
	 *
	 * @param id the id
	 * @return the customer by id
	 */
	CustomerResponse getCustomerById(Long id);

	/**
	 * Gets the customer by customer ID.
	 *
	 * @param customerID the customer ID
	 * @return the customer by customer ID
	 */
	CustomerResponse getCustomerByCustomerID(Long customerID);

	/**
	 * Save customer.
	 *
	 * @param customerRequest the customer request
	 * @return the customer response
	 */
	CustomerResponse saveCustomer(CustomerRequest customerRequest);

	/**
	 * Update customer.
	 *
	 * @param customerRequest the customer request
	 * @param id              the id
	 * @return the customer response
	 */
	CustomerResponse updateCustomer(CustomerRequest customerRequest, Long id);

	/**
	 * Delete customer.
	 *
	 * @param id the id
	 */
	void deleteCustomer(Long id);

}
