package com.neoris.accountservice.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountRequest {

	@NotBlank(message = "El numero de cuenta no puede ser vacio")
	@JsonProperty("Numero Cuenta")
	private String numCuenta;

	@NotBlank(message = "El tipo de cuenta no puede ser vacio")
	@JsonProperty("Tipo")
	private String tipoCuenta;

	
	@NotNull
	@JsonProperty("Saldo Inicial")
	private BigDecimal saldoInicial;

	@NotBlank(message = "El estado de cuenta no puede ser vacio")
	@JsonProperty("Estado")
	private String estado;
	
	@NotNull
	@JsonProperty("Cliente")
	private Long clienteId;

}
