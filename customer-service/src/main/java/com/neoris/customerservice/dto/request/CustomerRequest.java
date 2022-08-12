package com.neoris.customerservice.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerRequest {

	@NotNull
	@JsonProperty("Id Cliente")
	private Long clienteID;

	@JsonProperty("Contraseña")
	private String clave;

	@NotBlank(message = "El estado no puede ser vacio")
	@JsonProperty("Estado")
	private String estado;

	@NotBlank(message = "El nombre no puede ser vacio")
	@JsonProperty("Nombres")
	private String nombre;

	@NotBlank(message = "El genero no puede ser vacio")
	@JsonProperty("Genero")
	private String genero;

	@JsonProperty("Edad")
	private Integer edad;

	@NotBlank(message = "La identificacion no puede ser vacio")
	@JsonProperty("Identificacion")
	private String identificacion;

	@NotBlank(message = "La direccion no puede ser vacio")
	@JsonProperty("Direccion")
	private String direccion;

	@NotBlank(message = "El telefono no puede ser vacio")
	@JsonProperty("Telefono")
	private String telefono;

}
