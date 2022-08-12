package com.neoris.transactionservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.transactionservice.entity.TransactionEntity;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long> {

	List<TransactionEntity> findByNumCuenta(String numCuenta);

	List<TransactionEntity> findByFechaBetweenAndClienteId(LocalDateTime to, LocalDateTime from, Long clienteId);

}
