package com.primewash.proyecto_lp2.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrden {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coddet")
    private Integer codDetalle;

    @ManyToOne
    @JoinColumn(name = "codord")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "codser")
    private Servicio servicio;
    
    @ManyToOne
    @JoinColumn(name = "codpro")
    private Producto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "preuni")
    private BigDecimal preUni;

    // Mapeo de la columna calculada STORED en MySQL (solo lectura)
    @Column(name = "subtotal", insertable = false, updatable = false)
    private BigDecimal subtotal;
    
}