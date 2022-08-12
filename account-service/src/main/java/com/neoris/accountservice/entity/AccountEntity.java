package com.neoris.accountservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.neoris.accountservice.util.AccountStatus;
import com.neoris.accountservice.util.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class AccountEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9056097513407611669L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	/** The num cuenta. */
	@Column(name = "numero_cuenta", nullable = false)
	private String numCuenta;

	/** The cliente id. */
	@Column(name = "cliente_id", nullable = false, unique = true)
	private Long clienteId;

	/** The estado. */
	@Column(name = "estado", nullable = false)
	private AccountStatus estado;

	/** The tipo cuenta. */
	@Column(name = "tipo_cuenta", nullable = false)
	private AccountType tipoCuenta;

	/** The saldo inicial. */
	@Column(name = "saldo_inicial", nullable = false)
	private BigDecimal saldoInicial;

}
