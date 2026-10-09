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
@Table(name = "distrito")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert

public class Distrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coddis")
	private Integer codDistrito;
	
	@Column(name="nomdis")
	private String nomDistrito;

}