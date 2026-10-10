package com.primewash.proyecto_lp2.model;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "cliente")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codcli")
	private Integer codCliente;

	@Column(name = "tipodoc")
    private String tipoDocumento;
	
	@Column(name = "docid")
    private String docId;
	
	@Column(name = "nomcli")
	private String nomCliente;

	@Column(name = "apepcli")
	private String apePaternoCliente;

	@Column(name = "apemcli")
	private String apeMaternoCliente;

	@Column(name = "telcli")
	private String telCliente;

	@Column(name = "fecreg")
	private LocalDate fecRegistro;
	
	@ManyToOne
	@JoinColumn(name = "codsex")
	private Sexo sexo;

	@ManyToOne
	@JoinColumn(name = "coddis")
	private Distrito distrito;
}