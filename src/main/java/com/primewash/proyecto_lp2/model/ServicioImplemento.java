package com.primewash.proyecto_lp2.model;
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
@Table(name = "servicio_implemento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ServicioImplemento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codserimp")
	private Integer codSerImp;

	@ManyToOne
	@JoinColumn(name = "codser")
	private Servicio servicio;

	@ManyToOne
	@JoinColumn(name = "codimp")
	private Implemento implemento;

	@Column(name = "cantidad")
	private Integer cantidad;

}