package com.neoris.transactionservice.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TransactionRequest {

	@NotBlank(message = "El numero de cuenta no puede ser vacio")
	@JsonProperty("Numero Cuenta")
	private String numCuenta;

	@NotNull(message = "El valor de movimiento no puede ser nulo")
	@JsonProperty("Movimiento")
	private BigDecimal movimiento;

}
