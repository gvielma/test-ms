package com.neoris.customerservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "persons")
public class PersonEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2331763594214589667L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/** The nombre. */
	@Column(name = "nombre", nullable = false)
	private String nombre;

	/** The genero. */
	@Column(name = "genero", nullable = false)
	private String genero;

	/** The edad. */
	@Column(name = "edad", nullable = false)
	private Integer edad;

	/** The identificacion. */
	@Column(name = "identificacion", nullable = false, unique = true)
	private String identificacion;

	/** The direccion. */
	@Column(name = "direccion", nullable = false)
	private String direccion;

	/** The telefono. */
	@Column(name = "telefono", nullable = false)
	private String telefono;

}
