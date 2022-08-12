package com.neoris.accountservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("Id Cliente")
	private Long clienteID;

	@JsonProperty("Estado")
	private String estado;

	@JsonProperty("Nombres")
	private String nombre;

	@JsonProperty("Genero")
	private String genero;

	@JsonProperty("Edad")
	private Integer edad;

	@JsonProperty("Identificacion")
	private String identificacion;

	@JsonProperty("Direccion")
	private String direccion;

	@JsonProperty("Telefono")
	private String telefono;

}
