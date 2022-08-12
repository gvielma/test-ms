package com.neoris.customerservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.customerservice.entity.CustomerEntity;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {

	Optional<CustomerEntity> findByCodigoCliente(Long clienteId);
	Optional<CustomerEntity> findById(Long clienteId);

}
