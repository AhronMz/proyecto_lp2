package com.primewash.proyecto_lp2.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codord")
    private Integer codOrden;

    @Column(name = "numbol")
    private String numBoleta;

    @Column(name = "fecord")
    private LocalDateTime fecOrden;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "codcli")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codemp")
    private Empleado empleado;

    // Relación Bidireccional con el Detalle
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalles = new ArrayList<>();

    // Método utilitario para enlazar cada fila con esta cabecera
    public void addDetalle(DetalleOrden detalle) {
        detalles.add(detalle);
        detalle.setOrden(this);
    }
}