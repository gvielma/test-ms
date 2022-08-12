package com.neoris.transactionservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactionResponse {

	@JsonProperty("Fecha")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss a")
	private LocalDateTime fecha;

	@JsonProperty("Cliente")
	private String cliente;

	@JsonProperty("Numero Cuenta")
	private String numCuenta;

	@JsonProperty("Tipo")
	private String tipo;

	@JsonProperty("Saldo Inicial")
	private BigDecimal saldoInicial;

	@JsonProperty("Estado")
	private String estado;

	@JsonProperty("Movimiento")
	private BigDecimal movimiento;

	@JsonProperty("Saldo Disponible")
	private BigDecimal saldoDisponible;

}
