package com.neoris.accountservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.accountservice.dto.request.AccountRequest;
import com.neoris.accountservice.dto.request.TransactionRequest;
import com.neoris.accountservice.dto.response.AccountResponse;
import com.neoris.accountservice.dto.response.CustomerResponse;
import com.neoris.accountservice.entity.AccountEntity;
import com.neoris.accountservice.errorhandler.exception.AccountNotExistsException;
import com.neoris.accountservice.errorhandler.exception.ConflictException;
import com.neoris.accountservice.repository.IAccountRepository;
import com.neoris.accountservice.repository.ICustomerRepository;
import com.neoris.accountservice.util.AccountStatus;
import com.neoris.accountservice.util.AccountType;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public AccountResponse getAccount(String accountNumber) {
		AccountEntity accountEntity = accountRepository.findByNumCuenta(accountNumber)
				.orElseThrow(() -> new AccountNotExistsException(String.format("Account %s not exists", accountNumber),
						"ACCOUNT001"));

		CustomerResponse customerResponse = customerRepository.getCustomerByCustomerID(accountEntity.getClienteId())
				.getBody();

		return AccountResponse.builder().numCuenta(accountEntity.getNumCuenta())
				.tipoCuenta(accountEntity.getTipoCuenta().getType()).saldoInicial(accountEntity.getSaldoInicial())
				.estado(accountEntity.getEstado().getStatus()).cliente(customerResponse.getNombre())
				.clienteID(customerResponse.getClienteID()).build();
	}
	
	@Override
	public List<AccountResponse> getAccountsByClienteId(Long clienteID) {
		
		CustomerResponse customerResponse = customerRepository.getCustomerByCustomerID(clienteID).getBody();
		
		
		List<AccountResponse> accountResponseList = new ArrayList<AccountResponse>();
		
		List<AccountEntity> accountEntityList = accountRepository.findByClienteId(clienteID);
		
	    accountEntityList.forEach(e -> {
	    	accountResponseList.add(AccountResponse.builder()
	    			.numCuenta(e.getNumCuenta())
	    			.tipoCuenta(e.getTipoCuenta().getType())
	    			.saldoInicial(e.getSaldoInicial())
	    			.estado(e.getEstado().getStatus())
	    			.cliente(customerResponse.getNombre())
	    			.clienteID(customerResponse.getClienteID())
	    			.build());
	    });
		
		return accountResponseList;
	}


	@Override
	public AccountResponse saveAccount(AccountRequest accountRequest) {

		/** Se valida si existe la cuenta */
		accountRepository.findByNumCuenta(accountRequest.getNumCuenta()).ifPresent(r -> {

			/** Se Lanza Excepción */
			throw new ConflictException("Ya existe la cuenta " + accountRequest.getNumCuenta(), "ACCOUNT002");
		});

		CustomerResponse customerResponse = customerRepository.getCustomerByCustomerID(accountRequest.getClienteId())
				.getBody();

		if (null == customerResponse) {
			new ConflictException("No existe el cliente que desea asociar a la cuenta", "ACCOUNT007");
		}

		AccountEntity entity = AccountEntity.builder().numCuenta(accountRequest.getNumCuenta())
				.tipoCuenta(AccountType.fromType(accountRequest.getTipoCuenta()))
				.estado(AccountStatus.fromStatus(accountRequest.getEstado()))
				.saldoInicial(accountRequest.getSaldoInicial()).clienteId(accountRequest.getClienteId()).build();

		entity = accountRepository.save(entity);

		return AccountResponse.builder().cliente(customerResponse.getNombre()).estado(entity.getEstado().getStatus())
				.numCuenta(entity.getNumCuenta()).saldoInicial(entity.getSaldoInicial())
				.tipoCuenta(entity.getTipoCuenta().getType()).build();
	}

	@Override
	public AccountResponse updateAccount(AccountRequest accountRequest, Long id) {

		// Se valida que exista la cuenta
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ConflictException("No existe la cuenta a modificar", "ACCOUNT003"));

		// Se valida el cambio de numero de cuenta
		if (!accountRequest.getNumCuenta().equalsIgnoreCase(accountEntity.getNumCuenta())) {

			accountRepository.findByNumCuenta(accountRequest.getNumCuenta())
					.orElseThrow(() -> new ConflictException("Ya existe el numero de cuenta", "ACCOUNT004"));
		}

		CustomerResponse customerResponse = customerRepository.getCustomerByCustomerID(accountRequest.getClienteId())
				.getBody();

		if (null == customerResponse) {
			new ConflictException("No existe el cliente que desea asociar a la cuenta", "ACCOUNT008");
		}

		accountEntity.setClienteId(accountRequest.getClienteId());
		accountEntity.setEstado(AccountStatus.fromStatus(accountRequest.getEstado()));
		accountEntity.setNumCuenta(accountRequest.getNumCuenta());
		accountEntity.setSaldoInicial(accountRequest.getSaldoInicial());
		accountEntity.setTipoCuenta(AccountType.fromType(accountRequest.getTipoCuenta()));

		accountEntity = accountRepository.save(accountEntity);

		return AccountResponse.builder().cliente(customerResponse.getNombre())
				.estado(accountEntity.getEstado().getStatus()).numCuenta(accountEntity.getNumCuenta())
				.saldoInicial(accountEntity.getSaldoInicial()).tipoCuenta(accountEntity.getTipoCuenta().getType())
				.build();
	}

	@Override
	public void deleteAccount(Long id) {

		// Se valida que exista la cuenta
		AccountEntity accountEntity = accountRepository.findById(id)
				.orElseThrow(() -> new ConflictException("No existe la cuenta a desactivar", "ACCOUNT005"));

		accountEntity.setEstado(AccountStatus.FALSE);

		accountRepository.save(accountEntity);
	}

	@Override
	public void applyTransaction(TransactionRequest transactionRequest) {

		// Se valida que exista la cuenta
		AccountEntity accountEntity = accountRepository.findByNumCuenta(transactionRequest.getNumCuenta())
				.orElseThrow(() -> new ConflictException("No existe la cuenta a modificar", "ACCOUNT006"));


		accountEntity.setSaldoInicial(transactionRequest.getSaldoDisponible());

		accountRepository.save(accountEntity);
	}

}
