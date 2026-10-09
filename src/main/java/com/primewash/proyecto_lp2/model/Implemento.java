package com.primewash.proyecto_lp2.model;


import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "implemento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert

public class Implemento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codimp")
	private Integer codImplemento;
	
	@Column(name="nomimp")
	private String nomImplemento;
	
}
