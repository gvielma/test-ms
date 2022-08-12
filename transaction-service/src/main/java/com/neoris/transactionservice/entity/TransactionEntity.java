package com.neoris.transactionservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movimientos")
public class TransactionEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss a")
	@Column(name = "fecha")
	private LocalDateTime fecha;

	/** The num cuenta. */
	@Column(name = "numero_cuenta", nullable = false)
	private String numCuenta;

	/** The cliente id. */
	@Column(name = "cliente_id", nullable = false, unique = true)
	private Long clienteId;

	/** The saldo inicial. */
	@Column(name = "saldo_inicial", nullable = false)
	private BigDecimal saldoInicial;

	/** The saldo disponible. */
	@Column(name = "saldo_disponible", nullable = false)
	private BigDecimal saldoDisponible;

	/** The movimiento. */
	@Column(name = "movimiento", nullable = false)
	private BigDecimal movimiento;

}
