package com.neoris.transactionservice.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neoris.transactionservice.dto.request.TransactionMessage;
import com.neoris.transactionservice.dto.request.TransactionRequest;
import com.neoris.transactionservice.dto.response.AccountResponse;
import com.neoris.transactionservice.dto.response.TransactionResponse;
import com.neoris.transactionservice.entity.TransactionEntity;
import com.neoris.transactionservice.errorhandler.exception.AccountNotExistsException;
import com.neoris.transactionservice.errorhandler.exception.ConflictException;
import com.neoris.transactionservice.errorhandler.exception.SendEventException;
import com.neoris.transactionservice.kafka.EventPublisher;
import com.neoris.transactionservice.repository.IAccountRepository;
import com.neoris.transactionservice.repository.ITransactionRepository;

/**
 * The Class TransactionService.
 */
@Service
public class TransactionService implements ITransactionService {

	/** The topic name. */
	@Value("${spring.kafka.template.default-topic}")
	String topicName;

	/** The transaction repository. */
	@Autowired
	private ITransactionRepository transactionRepository;

	/** The account repository. */
	@Autowired
	private IAccountRepository accountRepository;

	/** The event publisher. */
	@Autowired
	private EventPublisher eventPublisher;

	/**
	 * Gets the transaction.
	 *
	 * @param accountNumber the account number
	 * @return the transaction
	 */
	@Override
	public List<TransactionResponse> getTransaction(String accountNumber) {

		List<TransactionEntity> transactionEntity = transactionRepository.findByNumCuenta(accountNumber);
		
		AccountResponse accountResponse = accountRepository.getAccountByAccountNumber(accountNumber).getBody();

		
		List <TransactionResponse> transactionList = new ArrayList<TransactionResponse>();
		
		transactionEntity.forEach(entity ->{
			
			transactionList.add(TransactionResponse.builder()
					.cliente(accountResponse.getCliente())
					.estado(accountResponse.getEstado())
					.fecha(entity.getFecha())
					.movimiento(entity.getMovimiento())
					.numCuenta(entity.getNumCuenta())
					.saldoDisponible(entity.getSaldoDisponible())
					.saldoInicial(entity.getSaldoInicial())
					.tipo(accountResponse.getTipoCuenta())
					.build());
		});;
		
		return transactionList;
	}
	
	
	/**
	 * Gets the transaction reports.
	 *
	 * @param accountNumber the account number
	 * @param fechaDesde the fecha desde
	 * @param fechaHasta the fecha hasta
	 * @return the transaction reports
	 */
	@Override
	public List<TransactionResponse> getTransactionReports(Long clienteID, LocalDateTime  fechaDesde, LocalDateTime  fechaHasta) {

		List<TransactionEntity> transactionEntity = transactionRepository.findByFechaBetweenAndClienteId(fechaDesde, fechaHasta,clienteID);
		
		List<TransactionResponse> transactionResponseList = new ArrayList<TransactionResponse>();
		
		List<AccountResponse> accountResponseList = accountRepository.getAccountsByClienteId(clienteID).getBody();
		
		transactionEntity.forEach(entity -> {
			
			Optional<AccountResponse> accountResponse = accountResponseList.stream()
					.filter(account -> account.getNumCuenta().equalsIgnoreCase(entity.getNumCuenta()))
					.findFirst();
		   
			accountResponse.ifPresent(account -> {
				
                       transactionResponseList.add(TransactionResponse.builder()
						.cliente(account.getCliente())
						.estado(account.getEstado())
						.fecha(entity.getFecha())
						.movimiento(entity.getMovimiento())
						.numCuenta(entity.getNumCuenta())
						.saldoDisponible(entity.getSaldoDisponible())
						.saldoInicial(entity.getSaldoInicial())
						.tipo(account.getTipoCuenta())
						.build());
                       });
		});
		
		return transactionResponseList;
	}

	/**
	 * Save transaction.
	 *
	 * @param transactionRequest the transaction request
	 * @return the transaction response
	 */
	@Override
	@Transactional
	public TransactionResponse saveTransaction(TransactionRequest transactionRequest) {

		AccountResponse accountResponse = accountRepository.getAccountByAccountNumber(transactionRequest.getNumCuenta())
				.getBody();

		if (null == accountResponse) {
			throw new AccountNotExistsException(
					String.format("La cuenta %s no existe ", transactionRequest.getNumCuenta()), "TRANSACTION002");
		}

		BigDecimal saldoDisponible = isTransferPossible(accountResponse.getSaldoInicial(),
				transactionRequest.getMovimiento());

		TransactionEntity entity = TransactionEntity.builder().numCuenta(transactionRequest.getNumCuenta())
				.clienteId(accountResponse.getClienteID()).saldoDisponible(saldoDisponible).fecha(LocalDateTime.now())
				.movimiento(transactionRequest.getMovimiento()).saldoInicial(accountResponse.getSaldoInicial()).build();

		entity = transactionRepository.save(entity);
		
 
		TransactionMessage message = TransactionMessage.builder().numCuenta(entity.getNumCuenta()).clienteId(entity.getClienteId()).saldoDisponible(saldoDisponible).build();
		
		try {
			eventPublisher.sendEvent(entity.getId(), message, topicName);
		} catch (Exception e) {
			throw new SendEventException("An error occurred while trying to transfer, please try again later", "TRANSACTION011");
		}

		return 	TransactionResponse.builder()
				.cliente(accountResponse.getCliente()).estado(accountResponse.getEstado())
				.fecha(entity.getFecha()).movimiento(entity.getMovimiento()).numCuenta(entity.getNumCuenta())
				.saldoDisponible(entity.getSaldoDisponible()).saldoInicial(entity.getSaldoInicial())
				.tipo(accountResponse.getTipoCuenta()).build();
	}

	/**
	 * Checks if is transfer possible.
	 *
	 * @param saldoInicial the saldo inicial
	 * @param movimiento   the movimiento
	 * @return the big decimal
	 */
	private BigDecimal isTransferPossible(BigDecimal saldoInicial, BigDecimal movimiento) {

		BigDecimal saldoDisponible = saldoInicial;

		if (movimiento.signum() < 0) {

			saldoDisponible = saldoInicial.subtract(movimiento.negate());

			if (saldoDisponible.signum() < 0) {

				throw new ConflictException("Saldo no disponible", "TRANSACTION003");
			}

		} else {
			saldoDisponible = saldoInicial.add(movimiento);
		}

		return saldoDisponible;
	}

}
