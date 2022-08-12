package com.neoris.accountservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.accountservice.entity.AccountEntity;

/**
 * The Interface IAccountRepository.
 */
@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {

	/**
	 * Find by num cuenta.
	 *
	 * @param numCuenta the num cuenta
	 * @return the optional
	 */
	Optional<AccountEntity> findByNumCuenta(String numCuenta);

	/**
	 * Find by cliente id.
	 *
	 * @param clienteID the cliente ID
	 * @return the list
	 */
	List<AccountEntity> findByClienteId(Long clienteID);

}
