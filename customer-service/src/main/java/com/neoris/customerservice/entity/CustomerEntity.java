package com.neoris.customerservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.neoris.customerservice.util.CustomerStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/** The codigo cliente. */
	@Column(name = "codigo_cliente", nullable = false)
	private Long codigoCliente;

	/** The clave. */
	@Column(name = "clave", nullable = false)
	private String clave;

	/** The estado. */
	@Column(name = "estado", nullable = false)
	private CustomerStatus estado;

	/** The person. */
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private PersonEntity person;

}
