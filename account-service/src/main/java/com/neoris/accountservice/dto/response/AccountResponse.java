package com.neoris.accountservice.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
	


	@JsonProperty("Numero Cuenta")
	private String numCuenta;

	@JsonProperty("Tipo")
	private String tipoCuenta;
	
	@JsonProperty("Saldo Inicial")
	private BigDecimal saldoInicial;
	
	@JsonProperty("Estado")
	private String estado;
	
	@JsonProperty("Cliente")
	private String cliente;
	
	@JsonProperty("Id Cliente")
	private Long clienteID;

}
