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
@Table(name = "servicio_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ServicioProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codserpro")
	private Integer codSerPro;

	@ManyToOne
	@JoinColumn(name = "codser")
	private Servicio servicio;

	@ManyToOne
	@JoinColumn(name = "codpro")
	private Producto producto;

	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "precio_unitario")
	private Double precioUnitario;

	@Column(name = "subtotal_producto", insertable = false, updatable = false) // Para que solo sea de lectura
	private Double subtotalProducto;

}