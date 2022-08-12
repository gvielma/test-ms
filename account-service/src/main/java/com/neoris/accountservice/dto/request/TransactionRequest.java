package com.neoris.accountservice.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class TransactionRequest.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

	/** The num cuenta. */
	private String numCuenta;

	/** The monto. */
	private BigDecimal saldoDisponible;

	/** The cliente id. */
	private Long clienteId;

}
