package com.primewash.proyecto_lp2.model;

import java.math.BigDecimal;
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
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert

public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codpro")
	private Integer codProducto;

	@Column(name = "nompro")
	private String nomProducto;

	@Column(name = "despro")
	private String despProducto;

	@Column(name = "prepro")
	private BigDecimal preProducto;

	@Column(name = "canpro")
	private Integer canProducto;
	
	@ManyToOne
	@JoinColumn(name = "codcat")
	private Categoria categoria;

	@Column(name = "fecing")
	private LocalDate fecIngreso;
}
