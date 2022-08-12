package com.neoris.transactionservice.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class TransactionRequest.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionMessage {

	/** The num cuenta. */
	private String numCuenta;

	/** The monto. */
	private BigDecimal saldoDisponible;

	/** The cliente id. */
	private Long clienteId;

}
